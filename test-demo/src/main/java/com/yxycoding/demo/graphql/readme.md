
> 1）graphql的依赖：graphql-spring-boot-starter、graphql-java-tools。
  
> （2）graphiql（graphql GUI，图形化工具不是必需的）：graphiql-spring-boot-starter；
> GraphiQL 是一个可以直接和 GraphQL 服务交互的 UI 界面，可以执行查询和修改请求。

```text
graphql-spring-boot-starter ：5.0.2

graphql-java-tools：5.2.4

graphiql-spring-boot-starter：7.1.0
```

```xml
<dependency>
        <groupId>com.graphql-java</groupId>
        <artifactId>graphql-spring-boot-starter</artifactId>
        <version>5.0.2</version>
    </dependency>  
    <dependency>
        <groupId>com.graphql-java</groupId>
        <artifactId>graphql-java-tools</artifactId>
        <version>5.2.4</version>
    </dependency>
    <dependency>
        <groupId>com.graphql-java-kickstart</groupId>
        <artifactId>graphiql-spring-boot-starter</artifactId>
        <version>7.1.0</version>
    </dependency>
```


> 启动之后，访问：http://127.0.0.1:10001/graphiql
>

