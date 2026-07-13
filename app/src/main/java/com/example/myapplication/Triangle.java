package com.example.myapplication;

import androidx.annotation.NonNull;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Double.compare(base, triangle.base) == 0 && Double.compare(height, triangle.height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(base, height);
    }

    @NonNull
    @Override
    public String toString() {
        return "------------------------\n" +
                "Name: " + getName() +
                "\nWidth: " + base +
                "\nHeight: " + height +
                "\nArea: " + area();
    }
}
