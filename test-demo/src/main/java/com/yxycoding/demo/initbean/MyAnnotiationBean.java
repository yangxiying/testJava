package com.yxycoding.demo.initbean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MyAnnotiationBean {

    @Bean
    public void myTest(){
        log.info("::::myTest is created");
    }
}
