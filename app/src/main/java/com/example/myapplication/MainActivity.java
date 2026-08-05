package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Async";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    // Worker pool for background tasks
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView resultTextView = findViewById(R.id.resultTextView);
        Button fetchButton = findViewById(R.id.fetchButton);

        fetchButton.setOnClickListener(view -> {

            Log.d(TAG, "Button clicked, starting background work.");

            // Run network call on a background thread
            executorService.execute(() -> {
                String title = fetchFirstPostTitle();

                // Switch back to main thread to update UI
                runOnUiThread(() -> {
                    Log.d(TAG, "Updating UI on main thread.");
                    resultTextView.setText(title);
                });
            });
        });
    }

    //Runs on background thread. Never update UI here!
    private String fetchFirstPostTitle() {
        try {
            //Get shared Retrofit instance
            Retrofit retrofit = RetrofitClient.getClient(BASE_URL);
            ApiService apiService = retrofit.create(ApiService.class);
            // execute() is synchronous, so it must run off the main thread
            Response<List<Post>> response = apiService.getPosts().execute();

            Log.d(TAG, "Network call finished on background thread: " + Thread.currentThread().getName());

            if (response.isSuccessful() && response.body() != null) {
                List<Post> posts = response.body();
                return posts.get(0).getTitle();
            } else {
                Log.e(TAG, "Response failed. Code: " + response.code());
                return "Request failed: " + response.code();
            }

        } catch (Exception e) {
            Log.e(TAG, "Network call failed: " + e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();// Release worker pool when activity closes
    }
}