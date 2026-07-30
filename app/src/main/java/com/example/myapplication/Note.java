package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//database class
@Entity(tableName = "notes")
public class Note {

    //system will automatically increase the id
    @PrimaryKey(autoGenerate = true)
    public int id;

    //column names
    @ColumnInfo(name = "title")
    public String title;


    @ColumnInfo(name = "content")
    public String content;

    //we need just title and, content. System give us the id.
    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
