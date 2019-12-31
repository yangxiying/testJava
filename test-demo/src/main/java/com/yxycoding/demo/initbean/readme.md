
- 使用 `InitializingBean` 可以 应用启动时运行代码 。参考代码 `MyAnnotiationBean`

代码如下：
```java
@Component
public class MyInitializingBean implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("::::::::::::MyInitializingBean is created");
    }
}
```

结果如下：

```log
com.yxycoding.demo.TestDemoApplication   : Starting TestDemoApplication on wgr with PID 11212 
com.yxycoding.demo.TestDemoApplication   : No active profile set, falling back to default profiles: default
c.y.demo.initbean.MyInitializingBean     : ::::::::::::MyInitializingBean is created
com.yxycoding.demo.TestDemoApplication   : Started TestDemoApplication in 0.809 seconds (JVM running for 1.628)
```

- 使用Bean注解，也可以在应用启动时运行，参考代码 `MyAnnotiationBean`

代码如下：
```java
@Configuration
public class MyAnnotiationBean {

    @Bean
    public void myTest(){
        log.info("::::myTest is created");
    }
}
```
运行如下：
```
com.yxycoding.demo.TestDemoApplication   : Starting TestDemoApplication on wgr with PID 11212 
com.yxycoding.demo.TestDemoApplication   : No active profile set, falling back to default profiles: default
c.y.demo.initbean.MyAnnotiationBean      : ::::myTest is created
com.yxycoding.demo.TestDemoApplication   : Started TestDemoApplication in 0.809 seconds (JVM running for 1.628)
```