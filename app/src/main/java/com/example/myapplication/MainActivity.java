package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.util.Log;
import android.widget.TextView;
import com.example.datehelper.DateFormatter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivitySDKLibrary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        TextView statusTextView = findViewById(R.id.statusTextView);

        String formattedDate = DateFormatter.formatNow();

        Log.d(TAG, "Formatted date: " + formattedDate);
        statusTextView.setText(formattedDate);

    }
}