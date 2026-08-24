package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import com.example.myapplication.security.AesManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Log.i(TAG, "Application started. Testing security module...");

        AesManager cryptoManager = new AesManager();

        // data
        String secretData = "SuperSecretManagerPassword123";
        Log.d(TAG, "Original Text: " + secretData);

        // encrypt data
        String encryptedData = cryptoManager.encryptData(secretData);
        Log.d(TAG, "Encrypted Text: " + encryptedData);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}