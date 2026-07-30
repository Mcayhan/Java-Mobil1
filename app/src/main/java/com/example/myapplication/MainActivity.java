package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "UserSettings";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_DARK_MODE = "dark_mode";
    private static final String TAG = "SharedPreferences";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        loadUserPreferences();
        saveUserPreferences("Muhammed Cayhan", true);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    private void saveUserPreferences(String username, boolean isDarkMode){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USERNAME, username);
        editor.putBoolean(KEY_DARK_MODE, isDarkMode);

        editor.apply();

        Log.i(TAG, "Data saved: " + username + "\nDark mode: " + isDarkMode);
    }

    private void loadUserPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        String savedUsername = sharedPreferences.getString(KEY_USERNAME, "Guest User");
        boolean isdarkModeEnabled = sharedPreferences.getBoolean(KEY_DARK_MODE, false);

        Log.i(TAG, "Loaded data: " + savedUsername + "\nDark mode: " + isdarkModeEnabled);
    }

}