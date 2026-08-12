package com.example.myapplication;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.datehelper.DateFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityDay19";
    private ExecutorService executorService = Executors.newSingleThreadExecutor();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextView postsTextView = findViewById(R.id.postsTextView);
        AppDatabase db = AppDatabase.getInstance(this);
        PostDao postDao = db.postDao();
        ApiService apiService = RetrofitClient.getApiService();

        // Ağdan veri çek
        apiService.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Post> posts = response.body();

                    executorService.execute(() -> {
                        List<PostEntity> entities = new ArrayList<>();
                        String fetchedAt = DateFormatter.formatNow();

                        for (Post post : posts) {
                            entities.add(new PostEntity(
                                    post.getId(),
                                    post.getUserId(),
                                    post.getTitle(),
                                    post.getBody(),
                                    fetchedAt
                            ));
                        }

                        postDao.insertAll(entities);
                        List<PostEntity> saved = postDao.getAll();

                        //UI threade dönüp ekrana yaz
                        runOnUiThread(() -> {
                            StringBuilder sb = new StringBuilder();
                            for (PostEntity entity : saved) {
                                sb.append(entity.id)
                                        .append(" - ").append(entity.title)
                                        .append("\n(Fetched at: ").append(entity.fetchedAt).append(")\n\n");
                            }
                            postsTextView.setText(sb.toString());
                        });
                    });
                } else {
                    Log.e(TAG, "Response not successful: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, "Network error", t);
            }
        });
    }
}