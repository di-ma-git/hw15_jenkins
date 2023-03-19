package org.example;

import jakarta.annotation.PostConstruct;

public class MyServiceImplProxy implements MyService {

    private MyService myService;

    @PostConstruct
    public void init() {
        System.out.println(" kek from myService impl");
    }

    MyServiceImplProxy(MyService myService) {
        this.myService = myService;
    }

    @Override
    public long getCurrentMilliseconds() {
        return myService.getCurrentMilliseconds();
    }

}
