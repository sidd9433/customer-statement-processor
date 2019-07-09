package com.rabobank.statementprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @SuppressWarnings("squid:S4823") // False positive
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
