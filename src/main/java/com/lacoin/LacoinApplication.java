package com.lacoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LacoinApplication {

    public static void main(String[] args) {
        SpringApplication.run(LacoinApplication.class, args);
    }
}
