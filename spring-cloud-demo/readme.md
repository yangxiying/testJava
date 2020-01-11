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

