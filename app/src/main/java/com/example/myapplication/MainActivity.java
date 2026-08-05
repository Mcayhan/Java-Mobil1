package com.example.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Permissions";

    private TextView statusTextView;

    // Modern way to request a permission and get the result back
    private final ActivityResultLauncher<String> permissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Log.d(TAG, "Camera permission granted.");
                    statusTextView.setText("Permission granted!");
                } else {
                    Log.d(TAG, "Camera permission denied.");
                    statusTextView.setText("Permission denied.");
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        statusTextView = findViewById(R.id.statusTextView);
        Button requestPermissionButton = findViewById(R.id.requestPermissionButton);

        requestPermissionButton.setOnClickListener(view -> checkAndRequestPermission());
    }

    private void checkAndRequestPermission() {
        boolean alreadyGranted = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED;

        if (alreadyGranted) {
            Log.d(TAG, "Permission was already granted.");
            statusTextView.setText("Already granted.");
        } else {
            Log.d(TAG, "Requesting camera permission.");
            permissionLauncher.launch(Manifest.permission.CAMERA);
        }
    }
}