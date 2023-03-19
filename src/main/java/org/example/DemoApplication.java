package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        System.out.println();

        MyServiceImpl myService = context.getBean("myServiceName", MyServiceImpl.class);
        myService.getNames();

        MyRepository repository = context.getBean("myRepository", MyRepository.class);
        System.out.println(repository.readFromFile());

//        MyService myService1  = context.getBean("myService", MyService.class)
//        context.put(MyService.class, myService);


    }

}
