package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.myapplication.adapters.NoteAdapter;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppDatabase database;
    private static final String TAG = "MainActivityLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "NoteDatabase").build();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // 1. Fetch notes from the database
                List<Note> savedNotes = database.noteDao().getAllNotes();

                // 2. Insert English mock data if the database is empty
                if (savedNotes.isEmpty()) {
                    Log.i(TAG, "Database is empty. Inserting test notes...");
                    database.noteDao().insertNote(new Note("Project Meeting", "Discuss the new UI requirements with the design team."));
                    database.noteDao().insertNote(new Note("Code Review", "Review pull requests for the backend authentication module."));
                    database.noteDao().insertNote(new Note("Update Documentation", "Write API documentation for the newly added endpoints."));

                    // Fetch the updated list after insertion
                    savedNotes = database.noteDao().getAllNotes();
                }

                Log.i(TAG, "Fetched " + savedNotes.size() + " notes from Room.");

                // 3. Prepare data for the UI thread
                final List<Note> finalNotes = savedNotes;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        NoteAdapter adapter = new NoteAdapter(finalNotes);
                        binding.notesRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        binding.notesRecyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }
}