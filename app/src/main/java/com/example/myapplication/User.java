package com.example.myapplication;

import androidx.annotation.NonNull;

public class User {

    public int id;
    public String name;
    public String email;

    @NonNull
    @Override
    public String toString() {
        return "Name: "+ name + "\nEmail: " + email;
    }
}
