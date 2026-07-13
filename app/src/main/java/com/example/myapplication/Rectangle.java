package com.example.myapplication;

public class Rectangle extends Shape{
    private double width;
    private double height;

    public Rectangle(String name, int width, int height) {
        super(name);
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width*height;
    }
}
