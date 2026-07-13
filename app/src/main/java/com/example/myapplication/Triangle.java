package com.example.myapplication;

public class Triangle extends Shape{

    private double base;
    private double height;

    public Triangle(String name, double base, double height) {
        super(name);
        this.base = base;
        this.height = height;
    }

    @Override
    public double area() {
        return (base*height)/2;
    }
}
