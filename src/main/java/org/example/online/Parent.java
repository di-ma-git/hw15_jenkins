package org.example.online;

public class Parent {

    int parentField = 2;
    static int parentFieldStatic =2;
    static {
        System.out.println("parent static");
    }

    public Parent(){
        System.out.println("parent contructor");
    }

    public String returnSome(){
        return "Parent";
    }

    public static String returnSomeStatic(){
        return "static";
    }


}
