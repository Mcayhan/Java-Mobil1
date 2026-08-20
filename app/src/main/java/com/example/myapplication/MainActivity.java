package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLPeerUnverifiedException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button testButton = findViewById(R.id.testPinButton);
        testButton.setOnClickListener(view -> testPinning());
    }

    private void testPinning() {
        Executors.newSingleThreadExecutor().execute(() -> {
            Request request = new Request.Builder()
                    .url("https://jsonplaceholder.typicode.com/posts/1")
                    .build();

            Call call = ApiClient.createClient().callFactory().newCall(request);

            try (Response response = call.execute()) {
                Log.i(TAG, "Request succeeded. Certificate pin matched. Code: " + response.code());
            } catch (SSLPeerUnverifiedException e) {
                Log.e(TAG, "Pin validation failed - certificate did not match", e);
            } catch (IOException e) {
                Log.e(TAG, "Network error", e);
            }
        });
    }
}