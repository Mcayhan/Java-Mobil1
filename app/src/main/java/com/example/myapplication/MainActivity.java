package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Concurrency";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        System.out.println("Hello World");

        Counter counter = new Counter();

        //first: Unsafe increment task
        Runnable unsafeTask = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementUnsafe();
            }
        };

        //second: Safe increment task
        Runnable safeTask = () -> {
            for (int i = 0; i < 10000; i++) {
                counter.incrementSafe();
            }
        };

        // Creating threads for unsafe operation
        Thread t1 = new Thread(unsafeTask, "Unsafe-Thread-1");
        Thread t2 = new Thread(unsafeTask, "Unsafe-Thread-2");

        // Creating threads for safe operation
        Thread t3 = new Thread(safeTask, "Safe-Thread-1");
        Thread t4 = new Thread(safeTask, "Safe-Thread-2");

        // Starting all threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Waiting for all threads to finish their execution before logging results
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            Log.e(TAG, "Thread execution was interrupted", e);
        }

        // Logging the final results to compare
        Log.d(TAG, "Expected Result: 20000");
        Log.d(TAG, "Unsafe Result (Race Condition): " + counter.getUnsafeCount());
        Log.d(TAG, "Safe Result (Synchronized): " + counter.getSafeCount());



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}