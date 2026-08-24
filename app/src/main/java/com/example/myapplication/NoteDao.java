package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    //adding new note method. Room will fill here.
    @Insert
    void insertNote(Note note);

    //listing code. sqll code for bring all table
    @Query("SELECT * FROM notes")
    List<Note> getAllNotes();

    //update note
    @Update
    void updateNote(Note note);

    //delete note
    @Delete
    void deleteNote(Note note);
}
