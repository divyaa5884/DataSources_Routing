- Created multiple datasources(here 3): based on a simple hash logic for id datasource<x> = (id % 3) + 1

        eg.  id%3 == 0 => data will be fetched/saved from datasource 1

- Used following to create db

    CREATE TABLE book_shard_x.book (
        id INTEGER auto_increment NOT NULL primary key,
        name TEXT NOT NULL,
        author TEXT NOT NULL
    )
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_0900_ai_ci;

- Resources followed:


  https://spring.io/guides/gs/accessing-data-mysql

  https://www.baeldung.com/spring-boot-configure-multiple-datasources

  https://spring.io/blog/2007/01/23/dynamic-datasource-routing

  https://dzone.com/articles/dynamic-multi-tenancy-using-java-spring-boot-sprin

- CURL commands implemented:

    1. POST:
        curl --location 'http://localhost:8082/api/book' \
        --header 'Content-Type: application/json' \
        --data '{
        "id": 7,
        "name": "Lukee",
        "author": "Lucas"
        }'
    
    2. GET:
        curl --location 'http://localhost:8082/api/book/4'
