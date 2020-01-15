> 此测试部署，主要是为了，spring-cloud-stream-kafka的学习时，可以连接kafka进行测试 


参考：[kafka原理及Docker环境部署](https://yq.aliyun.com/articles/657849)

从国内镜像拉取，[daocloud](https://hub.daocloud.io)
docker pull daocloud.io/library/zookeeper:3.5
修改下镜像名 docker tag daocloud.io/library/zookeeper:3.5 zk:3.5

docker pull wurstmeister/kafka:2.12-2.4.0
docker pull sheepkiller/kafka-manager:latest


docker pull daocloud.io/atsctoo/kafka:sgm
docker pull daocloud.io/atsctoo/kafka-manager:sgm

下面的为kafka官网的
```yml
version: '2'
services:
  zookeeper:
    image: wurstmeister/zookeeper
    ports:
      - "2181:2181"
  kafka:
    build: .
    ports:
      - "9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 192.168.99.100
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - /var/run/docker.sock:/var/run/docker.sock
```

- 下面为测试的
```yml
version: '2'
services:
  zookeeper:
    image: zk:3.5
    volumes:
      - ./zkdata:/data
    ports:
      - "2181:2181"
       
  kafka:
    image: wurstmeister/kafka:2.12-2.4.0
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_HOST_NAME: 172.24.76.52
      KAFKA_MESSAGE_MAX_BYTES: 2000000
      KAFKA_CREATE_TOPICS: "Topic1:1:3,Topic2:1:1:compact"
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
    volumes:
      - ./kafka_log:/kafka
      - /var/run/docker.sock:/var/run/docker.sock
 
  kafka-manager:
    image: sheepkiller/kafka-manager
    ports:
      - 9020:9000
    environment:
      ZK_HOSTS: zookeeper:2181
```



参数说明：

- KAFKA_ADVERTISED_HOST_NAME：`Docker宿主机IP`（如果你要配置多个brokers，就不能设置为 localhost 或 127.0.0.1）
  - modify the KAFKA_ADVERTISED_HOST_NAME in docker-compose.yml to match your docker host IP (Note: Do not use localhost or 127.0.0.1 as the host ip if you want to run multiple brokers.)
- KAFKA_MESSAGE_MAX_BYTES：kafka(message.max.bytes) 会接收单个消息size的最大限制，默认值为1000000 , ≈1M
- KAFKA_CREATE_TOPICS：初始创建的topics，可以不设置
  - Topic 1 will have 1 partition and 3 replicas, Topic 2 will have 1 partition, 1 replica and a cleanup.policy set to compact.
- 容器挂载./kafka_log 为防止容器销毁时消息数据丢失。
- 容器kafka-manager为yahoo出可视化kafka WEB管理平台。

- if you want to customize any Kafka parameters, simply add them as environment variables in docker-compose.yml, e.g. in order to increase the message.max.bytes parameter set the environment to KAFKA_MESSAGE_MAX_BYTES: 2000000. To turn off automatic topic creation set KAFKA_AUTO_CREATE_TOPICS_ENABLE: 'false'
- Kafka's log4j usage can be customized by adding environment variables prefixed with LOG4J_. These will be mapped to log4j.properties. For example: LOG4J_LOGGER_KAFKA_AUTHORIZER_LOGGER=DEBUG, authorizerAppender

```s
# 启动：
$ docker-compose up -d
 
# 增加更多Broker：
$ docker-compose scale kafka=3
 
# 合并：
$ docker-compose up --scale kafka=3

```

通过宿主机来访问 管理地址：

http://localhost:9020/

通过命令：
docker ps 找到kafka的容器名称

- 通过命令进入kafka容器：
docker exec -it kafka-demo /bin/bash

- 创建一个主题：
/opt/kafka/bin/kafka-topics.sh --create --zookeeper 172.24.76.52:2181 --replication-factor 1 --partitions 1 --topic my-test

```s
PS E:\myGitProject\testJava\docker-demo\kafka-demo> docker exec -it kafka-demo /bin/bash
bash-4.4#
bash-4.4# /opt/kafka/bin/kafka-topics.sh --create --zookeeper 172.24.76.52:2181 --replication-factor 1 --partitions 1 --topic my-test
Created topic my-test.
```
- 查看刚创建的主题
/opt/kafka/bin/kafka-topics.sh --list --zookeeper 172.24.76.52:2181

```shell
bash-4.4# /opt/kafka/bin/kafka-topics.sh --list --zookeeper 172.24.76.52:2181
Topic2
my-test
```

- 发送消息
/opt/kafka/bin/kafka-console-producer.sh --broker-list 172.24.76.52:9092 --topic my-test

```bash
bash-4.4# /opt/kafka/bin/kafka-console-producer.sh --broker-list 172.24.76.52:9092 --topic my-test
>this is a test msg
>this is another msg
>
>
>
>;
>exit
>quit;
>send
>;
>haha
```

- 读取消息
/opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server 172.24.76.52:9092 --topic my-test --from-beginning

```sh
bash-4.4# /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server 172.24.76.52:9092 --topic my-test --from-beginning
this is a test msg
this is another msg



;
exit
quit;
send
;
haha


```