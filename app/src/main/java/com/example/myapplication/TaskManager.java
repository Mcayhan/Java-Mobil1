package com.example.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class TaskManager {

    private static final String TAG = "TaskManager";
    private final List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
        Log.i(TAG, "Task added successfully: " + task.getTitle());
    }

    public Task getTaskById(int id) throws TaskNotFoundException {
        // Stream API kullanimi
        return taskList.stream()
                .filter(task -> task.getId() == id)
                .findFirst()
                .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));
    }

    public void completeTask(int id) {
        try {
            Task task = getTaskById(id);
            task.setCompleted(true);
            Log.i(TAG, "Task completed: " + task.getTitle());
        } catch (TaskNotFoundException e) {
            Log.e(TAG, "Failed to complete task. Error: " + e.getMessage());
        }
    }

    public void logAllTasks() {
        Log.d(TAG, "--- Task List ---");
        if (taskList.isEmpty()) {
            Log.d(TAG, "No tasks available.");
            return;
        }

        for (Task task : taskList) {
            Log.d(TAG, task.toString());
        }
    }


}








