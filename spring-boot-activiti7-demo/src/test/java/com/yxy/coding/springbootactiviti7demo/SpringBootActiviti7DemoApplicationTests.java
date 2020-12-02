package com.yxy.coding.springbootactiviti7demo;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringBootActiviti7DemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Resource
	RepositoryService repositoryService;

	@Resource
	RuntimeService runtimeService;

	@Resource
	TaskService taskService;

	@Test
	void test_activiti() {
		System.out.println("Number of process definitions : "
				+ repositoryService.createProcessDefinitionQuery().count());
		System.out.println("Number of tasks : " + taskService.createTaskQuery().count());
		runtimeService.startProcessInstanceByKey("oneTaskProcess");
		System.out.println("Number of tasks after process start: " + taskService.createTaskQuery().count());
	}
}
