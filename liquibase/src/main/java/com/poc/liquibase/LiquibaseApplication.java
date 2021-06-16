package com.poc.liquibase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.poc.liquibase"})
@EntityScan(basePackages = {"com.poc.liquibase"})
@EnableJpaRepositories(basePackages = {"com.poc.liquibase"})
@SpringBootApplication
public class LiquibaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(LiquibaseApplication.class, args);
    }

}
