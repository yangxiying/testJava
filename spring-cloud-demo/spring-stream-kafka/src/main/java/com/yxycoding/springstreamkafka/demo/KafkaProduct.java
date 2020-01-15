package com.yxycoding.springstreamkafka.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableBinding(value = {DemoKafka.class})
public class KafkaProduct {

    @Resource(name = DemoKafka.DEMO_OUTPUT)
    private MessageChannel sendDemoMessageChannel;

    @GetMapping("/sendMsga")
    public String sendShopMessagea(String content) {
        boolean isSendSuccess = sendDemoMessageChannel.
                send(MessageBuilder.withPayload(content).build());
        return isSendSuccess ? "发送成功" : "发送失败";
    }

    @Autowired
    private DemoKafka demoKafka;

    @GetMapping("/sendMsg")
    public String sendShopMessage(String content) {
        boolean send = demoKafka.sendDemoMessage().send(MessageBuilder.withPayload(content).build());
        return send ? "发送成功" : "发送失败";
    }

    @StreamListener(DemoKafka.DEMO_INPUT)
    public void receive(Message<String> message) {
        System.out.println(message.getPayload());
    }
}
