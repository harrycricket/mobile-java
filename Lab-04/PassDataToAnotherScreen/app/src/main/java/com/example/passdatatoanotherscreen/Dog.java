package com.example.passdatatoanotherscreen;

import java.io.Serializable;

public class Dog implements Serializable {
    private String name;
    private String color;
    private int age;

    public Dog(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog {" +
                "\n\t\tname=" + name +
                "\n\t\tcolor=" + color +
                "\n\t\tage=" + age +
                "\n}";
    }
}
