
# 加上相应jar包

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-stream-binder-kafka</artifactId>
</dependency>
```

# 定义接口

- 可以自定义接口，如下，有一个发送通过，一个接收的通道
```java
public interface DemoKafka {
    /**
     * 发消息的通道名称
     */
    String DEMO_OUTPUT = "demo_output";
    /**
     * 消息的订阅通道名称
     */
    String DEMO_INPUT = "demo_input";
    /**
     * 发消息的通道
     *
     * @return
     */
    @Output(DEMO_OUTPUT)
    MessageChannel sendDemoMessage();
    /**
     * 收消息的通道
     *
     * @return
     */
    @Input(DEMO_INPUT)
    SubscribableChannel recieveDemoMessage();
}
```

- 也可以使用jar包中自带的 `package org.springframework.cloud.stream.messaging` 包下

```java
// 发送通道
public interface Source {
    String OUTPUT = "output";

    @Output("output")
    MessageChannel output();
}
//接收通道
public interface Sink {
    String INPUT = "input";

    @Input("input")
    SubscribableChannel input();
}
//发送和接收在同一个接口中
public interface Processor extends Source, Sink {
}
```

# 发送接收消息

```java
/**
* The @EnableBinding annotation indicates that you want to bind your application to the messaging middleware. 
* The annotation takes one or more interfaces as a parameter — in this case,  //此注解可以接收多个接口参数
* the DemoKafka interface that defines an channel . 
* In the case of Kafka, messages sent to the output channel are, in turn, sent the Kafka topic.
*/
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
```


# config配置


```yaml
spring:
  application:
    name: kafka-demo
  cloud:
    stream:
      bindings:
        demo_input: # 和接口 DemoKafka 定义的管道值一定要一样，
          destination: demo # 当前管道对应的 topic
          group: demo
        demo_output: # 和接口 DemoKafka 定义的管道值一定要一样，
          destination: demo # 当前管道对应的 topic
          group: demo
      default-binder: kafka # 设置stream 使用 kafka
  kafka:
    bootstrap-servers: 172.24.76.52:9092  # 设置 kafka 服务地址
    consumer:
      enable-auto-commit: true
    producer:
      key-serializer: org.apache.kafka.common.serialization.ByteArraySerializer
      value-serializer: org.apache.kafka.common.serialization.ByteArraySerializer
```