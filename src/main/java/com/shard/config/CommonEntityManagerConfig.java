package com.shard.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.shard.repository",
        entityManagerFactoryRef = "customEntityManagerFactory",
        transactionManagerRef = "customTransactionManager"
)
public class CommonEntityManagerConfig {

    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder() {
        return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }

    @Bean(name = "customEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customEntityManagerFactory(
            @Qualifier("routingDataSource") DataSource routingDataSource) {
        System.out.println("Routing bean in custom factory " + routingDataSource);
        return entityManagerFactoryBuilder()
                .dataSource(routingDataSource)
                .packages("com.shard.model")
                .persistenceUnit("common-persistence-unit")
                .build();
    }

    @Bean(name = "customTransactionManager")
    public PlatformTransactionManager customTransactionManager(@Qualifier("customEntityManagerFactory") EntityManagerFactory customEntityManagerFactory) {
        return new JpaTransactionManager(customEntityManagerFactory);
    }
}
