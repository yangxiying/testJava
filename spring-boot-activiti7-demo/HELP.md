# springboot activiti7 的试用

## pom中加依赖

```xml
    <!--activiti7-->
    <dependency>
        <groupId>org.activiti</groupId>
        <artifactId>activiti-spring-boot-starter</artifactId>
        <version>7.1.0.M6</version>
    </dependency>
    <!-- Activiti生成流程图 -->
    <dependency>
        <groupId>org.activiti</groupId>
        <artifactId>activiti-image-generator</artifactId>
        <version>7.1.0.M6</version>
    </dependency>

    
```

- 由于activiti7使用的是 认证，需要加入相关依赖。  同时，在启动mian上去除自动认证
```xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
```

```java
//使用了spring security的话，那么每次访问都需要进行登录，特别不方便，可以关闭掉，修改启动类
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class}
		)
```

## 主要实现
- 通过starter引入依赖，可以直接使用相关服务   
  看 `LeaveController.java`


- 把bpmn文件放在 `resources/processes` 目录下，在项目启动的时候会自动发布相关流程。



