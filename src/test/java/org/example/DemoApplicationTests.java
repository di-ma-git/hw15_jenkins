package org.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(classes = MyTestConfig.class, properties = "spring.main.allow-bean-definition-overriding=true")
class DemoApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void contextLoads() throws InterruptedException {
		assertThat(applicationContext, is(notNullValue()));
		applicationContext.getBean("myNames");
		while (true){
//			Thread.sleep(1000);
		}
	}

}
