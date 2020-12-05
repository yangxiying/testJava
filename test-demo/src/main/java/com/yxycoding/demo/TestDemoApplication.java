package com.yxycoding.demo;

import com.yxycoding.demo.properties.PropertiesComm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.List;

@SpringBootApplication
@EnableAsync
public class TestDemoApplication {


	@Value("${per.sss}")
	private List<String> perList;

	@Value("${server.port}")
	private String perstr;

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(TestDemoApplication.class, args);

		PropertiesComm bean = run.getBean(PropertiesComm.class);
		bean.ssss();


		//如果使用new建对象，取不到 @Value 注解的值
//		PropertiesComm propertiesComm = new PropertiesComm();
//		propertiesComm.ssss();
	}

}
