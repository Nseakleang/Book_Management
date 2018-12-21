package com.example.rathana.bookmanagement_room.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rathana.bookmanagement_room.R;
import com.example.rathana.bookmanagement_room.entity.Author;
import com.example.rathana.bookmanagement_room.entity.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {


    List<Book>  books;
    Context mContext;

    public BookAdapter(List<Book> books, Context mContext) {
        this.books = books;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.book_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Book book= books.get(i);
        viewHolder.bookTitle.setText(book.title);
        viewHolder.bookPage.setText(book.page+"");
        viewHolder.dateCreate.setText(book.dateCreated);
        viewHolder.authorName.setText("Seakleang");
        Picasso.get().load(book.thumb)
                .resize(72,108)
                .into(viewHolder.bookThumb);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(List<Book> bookList) {
        this.books.addAll(bookList);
        notifyDataSetChanged();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        TextView bookTitle, authorName,dateCreate,bookPage;
        ImageView bookThumb;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle=itemView.findViewById(R.id.bookTitle);
            authorName=itemView.findViewById(R.id.authorName);
            dateCreate=itemView.findViewById(R.id.bookDateCreated);
            bookPage =itemView.findViewById(R.id.bookPage);
            bookThumb = itemView.findViewById(R.id.bookThumb);
        }
    }
}
