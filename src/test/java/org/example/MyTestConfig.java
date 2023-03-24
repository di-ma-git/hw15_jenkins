package org.example;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@TestConfiguration
public class MyTestConfig {

    @Bean
    public List<String> myNames(){
        return List.of("Kek");
    }


}
