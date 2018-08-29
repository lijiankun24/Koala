package com.lijiankun24.koalademo;

/**
 * MainActivity.java
 * <p>
 * Created by lijiankun24 on 2018/8/25.
 */
class Person {

    private int age;

    private String name;

    Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name + " age is " + age;
    }
}
