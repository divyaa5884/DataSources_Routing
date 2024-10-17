package com.shard.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.shard1")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.shard2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.shard3")
    public DataSource dataSource3() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "routingDataSource")
    public DataSource routingDataSource() {
        RoutingDataSource ds = new RoutingDataSource();
        HashMap<Object, Object> dsMap = new HashMap<>();
        dsMap.put("shard1", dataSource1());
        dsMap.put("shard2", dataSource2());
        dsMap.put("shard3", dataSource3());
        ds.setTargetDataSources(dsMap);
        ds.setDefaultTargetDataSource(dataSource3());
        return ds;
    }
}
