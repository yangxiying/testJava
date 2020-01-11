package com.yxycoding.springeurekaribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringEurekaRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEurekaRibbonApplication.class, args);
    }

}
