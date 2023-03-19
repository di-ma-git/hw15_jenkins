package org.example;

import java.util.ArrayList;
import java.util.List;

public class MyClass {

    List<String> names;
    MyServiceImpl myService;

    MyClass(){
        names = new ArrayList<>();
        myService = new MyServiceImpl();
    }


}
