package org.posapp.view.custom_components;

import lombok.Getter;

@Getter
public class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}