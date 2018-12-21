package com.example.rathana.bookmanagement_room.data.dao;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rathana.bookmanagement_room.entity.Author;
import com.example.rathana.bookmanagement_room.entity.Book;

import java.util.List;

public interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Book... books);
    @Update
    void update(Book... books);
    @Delete
    void delete(Book... books);

    @Query("SELECT * FROM book order by id asc")
    List<Book> getBooks();
}
