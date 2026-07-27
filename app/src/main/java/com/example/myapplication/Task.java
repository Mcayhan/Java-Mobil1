package com.example.myapplication;

import androidx.annotation.NonNull;

public class Task {
    private final int id;
    private String title;
    private boolean isCompleted;

    public Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.isCompleted = false;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        String status;

        if (isCompleted) {
            status = "Completed";
        } else {
            status = "Pending";
        }

        return "Task ID: " + id + " | Title: '" + title + "' | Status: " + status;
    }
}
