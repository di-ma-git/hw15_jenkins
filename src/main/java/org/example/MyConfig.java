package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MyConfig {
//IOC inversion of control

    @Value("${myKey}")
    String someValue;
    @Value("${myKey1}")
    String someValue1;
    @Bean
    File file() throws IOException {
        File file = new File("myRepository.txt");
        if(!file.exists()) file.createNewFile();
        return file;
    }

//    @Bean
//    public List<String> myNames(){
//        List<String> myNames = new ArrayList<>();
//        myNames.add("John");
//        myNames.add("Peter");
//        return myNames;
//    }
//    @Bean
//    public List<String> myNames2(){
//        List<String> myNames = new ArrayList<>();
//        myNames.add("John1");
//        myNames.add("Peter2");
//        return myNames;
//    }
    @Bean
    public List<String> myLowerCases(@Autowired List<String> vacavaca){
        List<String> names = new ArrayList<>();
        return names.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

//    @Bean
//    public


}
