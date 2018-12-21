package com.example.rathana.bookmanagement_room;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rathana.bookmanagement_room.adapter.AuthorAdapter;
import com.example.rathana.bookmanagement_room.data.dao.AuthorDao;
import com.example.rathana.bookmanagement_room.data.database.AuthorDatabase;
import com.example.rathana.bookmanagement_room.entity.Author;

import java.util.ArrayList;
import java.util.List;

public class AuthorActivity extends AppCompatActivity {

    List<Author> authors =new ArrayList<>();
    RecyclerView recyclerView;
    AuthorAdapter adapter;

    AuthorDatabase database;
    AuthorDao authorDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        database=AuthorDatabase.getAuthorDatabase(this);
        authorDao=database.authorDao();

        setupRecyclerView();
        getAuthorsFromRoom();
    }


    private void setupRecyclerView() {

        recyclerView=findViewById(R.id.authorRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AuthorAdapter(authors,this);
        recyclerView.setAdapter(adapter);
    }

    private void getAuthorsFromRoom() {
        List<Author> authorList=authorDao.getAuthors();
        adapter.setAuthors(authorList);
    }

}
