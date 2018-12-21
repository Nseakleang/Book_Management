package com.example.rathana.bookmanagement_room.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.rathana.bookmanagement_room.entity.Author;
import com.example.rathana.bookmanagement_room.entity.Book;

import java.util.List;

@Dao
public interface AuthorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Author ... authors);
    @Update
    void update(Author ... authors);
    @Delete
    void delete(Author ... authors);

    @Query("SELECT * FROM author order by id asc")
    List<Author> getAuthors();
}
