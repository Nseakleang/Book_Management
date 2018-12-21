package com.example.rathana.bookmanagement_room.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "book",
    foreignKeys = @ForeignKey(entity = Author.class,
            parentColumns = "id",
            childColumns = "author_id")
)
public class Book {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String desc;
    @ColumnInfo(name = "date_created")
    public String dateCreated;
    public int page;

    public String thumb;

    @ColumnInfo(name = "author_id")
    public int authorId;
}
