package com.yxycoding.springstreamkafka;

import com.yxycoding.springstreamkafka.demo.DemoKafka;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@SpringBootTest
class SpringStreamKafkaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource(name = DemoKafka.DEMO_OUTPUT)
    private MessageChannel sendDemoMessageChannel;




    @Test
    public void Demo() {
        boolean isSendSuccess = sendDemoMessageChannel.
                send(MessageBuilder.withPayload("this is test .OK??").build());
        System.out.println(isSendSuccess);

    }



}
