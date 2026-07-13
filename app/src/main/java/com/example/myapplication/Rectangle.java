package com.example.myapplication;

import androidx.annotation.NonNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(width, rectangle.width) == 0 && Double.compare(height, rectangle.height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @NonNull
    @Override
    public String toString() {
        return  "------------------------\n" +
                "Name: " + getName() +
                "\nWidth: " + width +
                "\nHeight: " + height +
                "\nArea: " + area();
    }


}
