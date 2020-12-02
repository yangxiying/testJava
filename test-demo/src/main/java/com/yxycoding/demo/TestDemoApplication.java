package com.yxycoding.demo;

import com.yxycoding.demo.properties.PropertiesComm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TestDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(TestDemoApplication.class, args);

		PropertiesComm bean = run.getBean(PropertiesComm.class);
		bean.ssss();

		//如果使用new建对象，取不到 @Value 注解的值
//		PropertiesComm propertiesComm = new PropertiesComm();
//		propertiesComm.ssss();
	}

}
