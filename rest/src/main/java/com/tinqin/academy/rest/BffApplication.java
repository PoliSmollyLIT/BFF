package com.tinqin.academy.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableFeignClients(basePackages = {"com.tinqin.academy"})
@ComponentScan(basePackages = {"com.tinqin.academy"})
@EntityScan(basePackages = {"com.tinqin.academy.persistence.models"})
@EnableJpaRepositories(basePackages = {"com.tinqin.academy.persistence.repositories"})
@SpringBootApplication()
public class BffApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffApplication.class, args);
    }

}
