package com.example.rathana.bookmanagement_room.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rathana.bookmanagement_room.R;
import com.example.rathana.bookmanagement_room.entity.Author;

import java.util.List;

public class AuthorAdapter  extends RecyclerView.Adapter<AuthorAdapter.ViewHolder> {


    List<Author>  authors;
    Context mContext;

    public AuthorAdapter(List<Author> authors, Context mContext) {
        this.authors = authors;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.author_item_layout,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Author author= authors.get(i);
        viewHolder.name.setText(author.name);
        viewHolder.gender.setText("Gender : "+author.gender);
        viewHolder.age.setText("Age : "+author.age+"");
        viewHolder.bookCount.setText("100");
    }

    @Override
    public int getItemCount() {
        return authors.size();
    }

    public void setAuthors(List<Author> authorList) {
        this.authors.addAll(authorList);
        notifyDataSetChanged();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        TextView name, bookCount,gender,age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            bookCount=itemView.findViewById(R.id.bookCount);
            gender=itemView.findViewById(R.id.gender);
            age =itemView.findViewById(R.id.age);
        }
    }
}
