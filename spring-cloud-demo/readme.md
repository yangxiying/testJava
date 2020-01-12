> 基于spring-boot 2.2.2 版本

# 注册中心 eureka

> 使用 `spring-cloud-starter-netflix-eureka-server` 生成一个注册中心

其中要注意，yml中的两个配置

```yaml
eureka:
  instance:
    hostname: localhost
  client:
    register-with-eureka: false # 必须设为 false，表明这本身是一个注册中心，是被用来注册的
    fetch-registry: false  # 必须设为 false，表明这本身是一个注册中心，是被用来注册的
    service-url:
      default-zone: http://${eureka.instance.hostname}:${server.port}/eureka/
```

## 服务注册

> 服务注册中 2.0版本中区分了一个 `spring-cloud-starter-netflix-eureka-client` 使用这个client时需要再引入 `spring-boot-starter-web`

> 也可以直接还是使用 `spring-cloud-starter-netflix-eureka-server`

## 服务调用

> 服务调用可以通过 `ribbon` 、 `feign` 。feign中默认包含了 ribbon。    
一般开发引入 feign 就可以


# 网关 zuul

## 简单的网关配置如下：

```yaml
zuul:
  routes:
    #标识你服务的名字，这里可以自己定义，一般方便和规范来讲还是跟自己服务的名字一样
    one-client:
      #服务映射的路径，通过这路径就可以从外部访问你的服务了，目的是为了不爆露你机器的IP，面向服务的路由了，给你选一个可用的出来，
      #这里zuul是自动依赖hystrix，ribbon的，不是面向单机
      path: /one/**
      #这里一定要是你Eureka注册中心的服务的名称，之所以这里配置serviceId因为跟eureka结合了，如果单独使用zuul,那么就必须写自己机器的IP了，
      #如url:http://localhost:8080/  这样的不好就是写死IP了，万一这IP挂了，这高可用性，服务注册那套东西就用不起来了
      serviceId: one-client

      # 如上配置之后 三种方式都可以访问
      # 1.原路径 http://localhost:8088/demo/hi?name=yxy
      # 2.zuul未经过path的：http://localhost:9090/one-client/demo/hi?name=yxy
      # 3.zuul经过path的：http://localhost:9090/one/demo/hi?name=yxy
```

## 测试负载

- 把 one-client 打包，通过命令启动多个
`java -jar -Dserver.port=8078 spring-eureka-client-0.0.1-SNAPSHOT.jar`

- 观察 eureka one-client 上有两个注册的
```
Application	AMIs	Availability Zones	Status
ONE-CLIENT	n/a (2)	(2)	UP (2) - host.docker.internal:one-client:8088 , host.docker.internal:one-client:8078
```

- 调用测试接口测试，使用zuul的地址测试，看其是否负载到两个服务上

`http://localhost:9090/one/demo/hi?name=yxy`


## 链路跟踪

使用 zipkim 。部署zipkim。

- 通过 `docker` 来部署
`docker run -d -p 9411:9411 openzipkin/zipkin`
 这个默认数据存在内存中，使用是h2。测试时可以使用，重启之后内存中数据丢失
 
 - 存放在 `mysql` 数据库中
建表语句在官网git上查看：
`https://github.com/openzipkin/zipkin/blob/master/zipkin-storage/mysql-v1/src/main/resources/mysql.sql`

```
docker run -d  -p 9411:9411 -e MYSQL_USER=root -e MYSQL_PASS=123456 -e MYSQL_HOST=host.docker.internal -e STORAGE_TYPE=mysql -e MYSQL_DB=zipkim-demo -e MYSQL_TCP_PORT=3308 openzipkin/zipkin
```
- docker-compose 的配置
```yaml
version: '2'

services: 
    zipkin:
        image: openzipkin/zipkin
        container_name: zipkin
        environment: 
            - STORAGE_TYPE=mysql
            - MYSQL_USER=root
            - MYSQL_PASS=123456
            - MYSQL_DB=zipkim-demo
            - MYSQL_HOST=host.docker.internal
            - MYSQL_TCP_PORT=3308
            - MYSQL_PASSWORD_CHARACTER_ENCODING=UTF-8
            - MYSQL_CHARACTER_ENCODING=UTF-8
        ports:
            - 9411:9411
```


在相应的服务的配置中配置上zipkim

- 添加依赖jar
```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zipkin</artifactId>
        </dependency>
```

- 添加配置

```yaml
spring:
  zipkin:
    base-url: http://localhost:9411/
  sleuth:
    sampler:
      probability: 1.0 #请求的采样率，在测试时为了方便查看可以改为1表示所有请求都记录，但在生产环境中还是建议改为0.1，否则数量太多影响性能
```

访问 地址，查看调用链
`http://127.0.0.1:9411/zipkin/`
