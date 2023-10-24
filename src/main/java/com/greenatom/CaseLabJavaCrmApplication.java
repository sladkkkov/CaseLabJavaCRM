package com.greenatom;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class CaseLabJavaCrmApplication {
    @Value("${spring.datasource.password}")
    private static String password;
    @Value("${spring.datasource.username}")
    private static String username;
    @Value("${spring.datasource.database}")
    private static String database;

    public static void main(String[] args) {
        SpringApplication.run(CaseLabJavaCrmApplication.class, args);
    }

    @PostConstruct
    public void init() {
        log.info(password);
        log.info(username);
        log.info(database);
    }

}
