package com.example.rathana.bookmanagement_room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "author")
public class Author {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public int age;
    public String gender;

}
