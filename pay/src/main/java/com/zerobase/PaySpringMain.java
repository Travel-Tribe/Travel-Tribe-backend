package com.zerobase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PaySpringMain {

    public static void main(String[] args) {
        SpringApplication.run(PaySpringMain.class, args);
    }
}