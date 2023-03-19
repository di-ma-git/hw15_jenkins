package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MyConfig {
//IOC inversion of control


    @Bean
    public List<String> myNames(){
        List<String> myNames = new ArrayList<>();
        myNames.add("John");
        myNames.add("Peter");
        return myNames;
    }

    @Bean
    public List<String> myLowerCases(){
        List<String> names = new ArrayList<>();
        names.add("Kek");
        return names.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

//    @Bean
//    public


}
