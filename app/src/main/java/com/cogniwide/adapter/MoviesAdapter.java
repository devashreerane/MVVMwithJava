package com.cogniwide.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.cogniwide.R;
import com.cogniwide.model.Result;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Result> moviesList;
    private Context mContext;
    private String POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_title, txt_releaseDate;
        public ImageView imgV_movie;

        public MyViewHolder(View view) {
            super(view);
            txt_title = view.findViewById(R.id.txt_title);
            txt_releaseDate =  view.findViewById(R.id.txt_releaseDate);
            imgV_movie =  view.findViewById(R.id.imgV_movie);
        }
    }


    public MoviesAdapter(Context context,List<Result> moviesList) {
        this.moviesList = moviesList;
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rv_movies_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Result movie = moviesList.get(position);
        holder.txt_title.setText(movie.getTitle());
        holder.txt_releaseDate.setText(movie.getReleaseDate());

        Glide.with(mContext)
                .load(POSTER_BASE_URL+movie.getPosterPath())
                .into(holder.imgV_movie);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}

