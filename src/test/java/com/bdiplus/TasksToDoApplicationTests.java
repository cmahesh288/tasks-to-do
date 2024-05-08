package com.bdiplus;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.bdiplus.controller.TasksControllerTests;

@SpringBootTest(classes = {TasksToDoApplicationTests.class, TasksControllerTests.class})
class TasksToDoApplicationTests {

	@Test
	void contextLoads() {
	}

}
