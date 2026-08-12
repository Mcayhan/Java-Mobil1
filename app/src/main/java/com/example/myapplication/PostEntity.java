package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class PostEntity {
    @PrimaryKey
    public int id;
    public int userId;
    public String title;
    public String body;
    public String fetchedAt;

    public PostEntity(int id, int userId, String title, String body, String fetchedAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.fetchedAt = fetchedAt;
    }
}
