package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private static final String TAG = "Database";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //sqlite, room

        //building database
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "NoteDatabase").build();

        //use executor to run database operations on a background thread
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //insert a new note
                Note myNote = new Note("Shopping", "Milk, Eggs, Bread");
                database.noteDao().insertNote(myNote);
                Log.i(TAG,"Note successfully inserted.");

                //fetch and log all notes
                List<Note> savedNotes = database.noteDao().getAllNotes();
                for (Note n : savedNotes) {
                    Log.i(TAG, "Fetched note: " + n.title + " " + n.content);
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}