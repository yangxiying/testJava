package com.yxycoding.springstreamkafka;

import com.yxycoding.springstreamkafka.demo.DemoKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.util.StringUtils;

@SpringBootApplication

public class SpringStreamKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStreamKafkaApplication.class, args);
    }

}
