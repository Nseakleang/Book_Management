package com.example.rathana.bookmanagement_room;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rathana.bookmanagement_room.adapter.BookAdapter;
import com.example.rathana.bookmanagement_room.data.dao.AuthorDao;
import com.example.rathana.bookmanagement_room.data.dao.BookDao;
import com.example.rathana.bookmanagement_room.data.database.AuthorDatabase;
import com.example.rathana.bookmanagement_room.entity.Author;
import com.example.rathana.bookmanagement_room.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton btnCreateAuthor, btnCreateBook;
    private Author newAuthor;
    private Book newBook;
    AuthorDatabase database;
    AuthorDao authorDao;
    BookDao bookDao;
    TextView bookAuthorName;
    EditText bookAuthorID;
    List<Author> authorList;
    //RecyclerView
    RecyclerView recyclerView;
    BookAdapter bookAdapter;
    List<Book> books = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateAuthor =findViewById(R.id.createAuthor);
        btnCreateBook = findViewById(R.id.createBook);

        //create room database
        database=AuthorDatabase.getAuthorDatabase(this);
        authorDao=database.authorDao();
        bookDao= database.bookDao();

        //set bookRecyclerView
        setupBookRecyclerView();
        getBooks();

        btnCreateAuthor.setOnClickListener(v->{
            createAuthorDialog();
        });
        btnCreateBook.setOnClickListener(v -> {
            createBookDialog();
        });
    }

    private void getBooks() {
        List<Book> books = bookDao.getBooks();
        bookAdapter.setBooks(books);
    }

    private void setupBookRecyclerView() {
        recyclerView = findViewById(R.id.bookRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        bookAdapter = new BookAdapter(books, this);
        recyclerView.setAdapter(bookAdapter);
    }

    private void createAuthorDialog(){

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Add new Author");

        //body
        View view=LayoutInflater.from(this).inflate(
                R.layout.add_author_dialog_layout,null
        );
        builder.setView(view);
        //create ui control objects
        EditText authorName=view.findViewById(R.id.name);
        EditText authorAge=view.findViewById(R.id.age);
        RadioGroup rdGroup=view.findViewById(R.id.rdGroup);
        newAuthor=new Author();
        //get data from radio button
        rdGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rdButton= group.findViewById(checkedId);

                if(rdButton.isChecked())
                    newAuthor.gender=rdButton.getText().toString();
                    //Toast.makeText(MainActivity.this,rdButton.getText().toString()+" checked",Toast.LENGTH_SHORT).show();

            }
        });


        builder.setCancelable(false);
        //footer
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                newAuthor.name=authorName.getText().toString();
                newAuthor.age=Integer.parseInt(authorAge.getText().toString());
                //insert author into table
                authorDao.add(newAuthor);
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "save success", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void createBookDialog(){
        //get all author
        authorList = authorDao.getAuthors();

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Add new Book");

        //body
        View view=LayoutInflater.from(this).inflate(
                R.layout.add_book_dialog_layout,null
        );
        builder.setView(view);
        //create ui control objects
        bookAuthorName=view.findViewById(R.id.authorName);
        bookAuthorID=view.findViewById(R.id.authorID);
        EditText bookTitle=view.findViewById(R.id.bookTitle);
        EditText bookPage=view.findViewById(R.id.bookPage);
        EditText bookDate=view.findViewById(R.id.bookDateCreated);
        EditText bookDesc=view.findViewById(R.id.bookDesc);
        EditText bookThumb=view.findViewById(R.id.bookThumb);

        newBook = new Book();
        setUpAuthorDropdown();
        builder.setCancelable(false);
        //footer
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                newBook.title=bookTitle.getText().toString();
                newBook.page=Integer.parseInt(bookPage.getText().toString());
                newBook.dateCreated=bookDate.getText().toString();
                newBook.desc=bookDesc.getText().toString();
                newBook.thumb=bookThumb.getText().toString();
                //insert author into table
                bookDao.add(newBook);
                dialog.dismiss();
                //Toast.makeText(MainActivity.this, "save success", Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void setUpAuthorDropdown() {
        //Create author array string
        CharSequence[] authorName = new CharSequence[authorList.size()];
        for (int i=0;i<authorName.length;i++){
            authorName[i] = authorList.get(i).name;
        }
        bookAuthorName.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setItems(authorName, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    bookAuthorName.setText(authorList.get(which).name);
                    bookAuthorID.setText(authorList.get(which).id+"");
                    dialog.dismiss();
                }
            });
            builder.create().show();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.author:
                startActivity(new Intent(this,AuthorActivity.class));
                return true;

            default : return  false;
        }
    }
}
