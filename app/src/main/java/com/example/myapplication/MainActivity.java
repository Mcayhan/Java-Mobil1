package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static final String TAG = "MainActivityLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Log.d(TAG, "onCreate: Activity started successfully.");

        //Event Handling
        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userInput = binding.inputEditText.getText().toString().trim();

                if (userInput.isEmpty()) {
                    Log.w(TAG, "onClick: User tried to submit an empty string.");

                    binding.displayTextView.setText("Please enter a valid text!");

                    Toast.makeText(MainActivity.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i(TAG, "onClick: Valid input received: " + userInput);

                    String displayText = "You entered: " + userInput;
                    binding.displayTextView.setText(displayText);

                    binding.inputEditText.setText("");
                }
            }
        });
    }
}