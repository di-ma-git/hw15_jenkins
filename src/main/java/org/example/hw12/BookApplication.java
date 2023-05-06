package org.example.hw12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableConfigurationProperties(MariaBDBookRepository.class)
//@ConfigurationPropertiesScan
public class BookApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BookApplication.class, args);
        System.out.println();
    }
}
