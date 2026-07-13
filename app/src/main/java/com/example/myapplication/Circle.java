package com.example.myapplication;

import androidx.annotation.NonNull;

import java.sql.SQLOutput;
import java.util.Objects;

public class Circle extends Shape{
    private double radius;

    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }

    @Override
    public double area() {
        return (Math.PI)*(Math.pow(radius,2));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(radius, circle.radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(radius);
    }

    @NonNull
    @Override
    public String toString() {
        return  "------------------------\n" +
                "Name: " + getName() +
                "\nRadius: " + radius +
                "\nArea: " + area();

    }
}
