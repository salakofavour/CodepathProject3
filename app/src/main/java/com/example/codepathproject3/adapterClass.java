package com.example.codepathproject3;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.bumptech.glide.Glide;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class adapterClass extends RecyclerView.Adapter<adapterClass.MyViewHolder>{
    Context context;
    ArrayList<model> combine;

    public adapterClass(Context context, ArrayList<model> combine) {
        this.context = context;
        this.combine = combine;
    }

    @NonNull
    @Override
    public adapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trying, parent, false);
        Log.i("tag", "layout");

        return new adapterClass.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapterClass.MyViewHolder holder, int position) {

        holder.Title.setText(combine.get(position).getTitle());
        holder.Body.setText(combine.get(position).getBody());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" +(combine.get(position).getPicture())).into(holder.Picture);

    }

    @Override
    public int getItemCount() {
        return combine.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Title, Body;
        ImageView Picture;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.title);
            Body = itemView.findViewById(R.id.body);
            Picture = itemView.findViewById(R.id.picture);
        }
    }
}
