
# aop

- 引入jar包 
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

## 一种实现方式：
### 通过 MethodInterceptor 来实现 拦截前后的 处理业务

参考： `MyMethodInterceper`

### 通过 以下 方法 织入

参考： `InterceptorConfig` 或者 `MyInitBean`

## 一种实现方式 ，使用 `@Aspect` 来实现

参考： `MyAspect`

