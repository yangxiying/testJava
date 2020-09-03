package com.yxycoding.demo;

import com.yxycoding.demo.properties.PropertiesComm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDemoApplication.class, args);


		PropertiesComm propertiesComm = new PropertiesComm();
		propertiesComm.ssss();
	}

}
