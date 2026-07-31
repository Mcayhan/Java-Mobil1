package com.example.myapplication;

import android.os.Bundle;
import retrofit2.Call;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "NetworkApiTask";
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private TextView tvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvUsers = findViewById(R.id.tvUsers);
        fetchUsersFromApi();


    }

    private void fetchUsersFromApi() {
        ApiService apiService = RetrofitClient.getClient(BASE_URL).create(ApiService.class);

        Call<List<User>> call = apiService.getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<User> userList = response.body();

                    StringBuilder stringBuilder = new StringBuilder();

                    for (User user : userList) {
                        stringBuilder.append("Name: ").append(user.name).append("\n");
                        stringBuilder.append("Email: ").append(user.email).append("\n\n");
                    }

                    tvUsers.setText(stringBuilder.toString());
                    Log.i(TAG, "Data successfully displayed on the UI.");
                } else {

                    tvUsers.setText("Error fetching data from server: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                tvUsers.setText("Connection error: Please check your internet.\n" + t.getMessage());
            }
        });
    }
}