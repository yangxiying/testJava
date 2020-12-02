package com.yxy.coding.springbootactiviti7demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//使用了spring security的话，那么每次访问都需要进行登录，特别不方便，可以关闭掉，修改启动类
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
//		ManagementWebSecurityAutoConfiguration.class}
//		)
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class,
		ManagementWebSecurityAutoConfiguration.class}
		)
public class SpringBootActiviti7DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootActiviti7DemoApplication.class, args);
	}

}
