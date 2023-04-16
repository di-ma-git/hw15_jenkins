package org.example.hw12;

import org.example.DemoApplication;
import org.example.hw12.repository.MariaBDBookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableConfigurationProperties(MariaBDBookRepository.class)
//@ConfigurationPropertiesScan
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        System.out.println("");
    }
}
