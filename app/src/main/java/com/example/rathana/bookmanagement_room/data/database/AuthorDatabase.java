package com.example.rathana.bookmanagement_room.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.rathana.bookmanagement_room.data.dao.AuthorDao;
import com.example.rathana.bookmanagement_room.data.dao.BookDao;
import com.example.rathana.bookmanagement_room.entity.Author;
import com.example.rathana.bookmanagement_room.entity.Book;

@Database(version = 1,entities = {Author.class,Book.class})
public abstract class AuthorDatabase  extends RoomDatabase {

    public abstract AuthorDao authorDao();
    public abstract BookDao bookDao();

    static final String DB_NAME="book_db";

    public static AuthorDatabase getAuthorDatabase(Context context){
        return Room.databaseBuilder(context,AuthorDatabase.class,DB_NAME)
                .allowMainThreadQueries()
                .build();
    }
}
