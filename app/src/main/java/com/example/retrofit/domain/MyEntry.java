package com.example.retrofit.domain;

public class MyEntry {
    private String name;
    private int age;
    public String address;
    public int salary;

    @Override
    public String toString() {
        return "MyEntry{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
