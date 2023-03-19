package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("myServiceName")
public class MyServiceImpl {

    @Autowired
    List<String> myNames;

    public long getCurrentMilliseconds() {
        return System.currentTimeMillis();
    }

}
