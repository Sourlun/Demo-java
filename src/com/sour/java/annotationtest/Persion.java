package com.sour.java.annotationtest;


/**
 *
 *
 * @author xgl
 * @date 2020/9/6 14:11
 **/
@MyAnnotation("hello")
public class Persion {

    @MyAnnotation
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
