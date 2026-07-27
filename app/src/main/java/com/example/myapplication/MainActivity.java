package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        System.out.println("Hello World");


        TaskManager taskManager = new TaskManager();

        // add task
        taskManager.addTask(new Task(1, "Setup Android Studio Environment"));
        taskManager.addTask(new Task(2, "Complete Day 8 Task Manager Consolidation"));

        // listing
        taskManager.logAllTasks();

        // complete task
        taskManager.completeTask(1);

        // exception test
        taskManager.completeTask(99);

        // currents status
        taskManager.logAllTasks();



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}