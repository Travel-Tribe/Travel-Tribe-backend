package com.zerobase.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients // FeignClient 활성화
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}