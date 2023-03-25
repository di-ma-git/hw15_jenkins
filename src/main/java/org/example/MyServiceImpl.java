package org.example;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("myServiceName")
@AllArgsConstructor
public class MyServiceImpl {

    MyRepository myRepository;

    private List<String> myNames;

    public List<String> getNames(){
        myRepository.writeToFile("was call to getNames at: " + System.currentTimeMillis());
        return myNames;
    }

    public long getCurrentMilliseconds() {
        return System.currentTimeMillis();
    }

}
