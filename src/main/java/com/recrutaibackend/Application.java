package com.recrutaibackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RecrutaiBackEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecrutaiBackEndApplication.class, args);
    }

}
