package org.example.online;

public class Child extends Parent {

    int parentField =3;
    static int parentFieldStatic =3;

    static {
        System.out.println("child static");
    }

    Child() {
        super();
        System.out.println("child contructor");
    }

    public String returnSome() {
        return "Child";
    }

    public static String returnSomeStatic() {
        return "static child";
    }
}
