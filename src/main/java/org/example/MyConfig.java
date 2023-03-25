package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MyConfig {
//IOC inversion of control

    @Value("classpath:citizen1000.json")
    private Resource resource;

    @Bean
    List<Citizen> allCitizensFromFile() {
        try (InputStream inputStream = resource.getInputStream()) {
            String json = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json, new TypeReference<List<Citizen>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Initialization of list citizen failed:" + e.getMessage());
        }
    }


    @Bean
    File file() throws IOException {
        File file = new File("myRepository.txt");
        if (!file.exists()) file.createNewFile();
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
//    @Bean
//    public List<String> myLowerCases(@Autowired List<String> vacavaca) {
//        List<String> names = new ArrayList<>();
//        return names.stream().map(String::toLowerCase).collect(Collectors.toList());
//    }

//    @Bean
//    public


}
