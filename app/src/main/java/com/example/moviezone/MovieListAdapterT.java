package com.example.moviezone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieListAdapterT extends RecyclerView.Adapter{
    private static List<Movie> movieList;
    private Context context;

    public static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView movieTitle;
        private TextView movieRating;
        private ImageView movieImage;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            movieImage = itemView.findViewById(R.id.movieListImage);
            movieRating = itemView.findViewById(R.id.movieListRating);
            movieTitle = itemView.findViewById(R.id.movieListTitle);

        }

        @Override
        public void onClick(View v) {
           int position = getAdapterPosition();
           final Movie movie = movieList.get(position);

           if(v.getId() == R.id.movieListItem){

           }

        }

    }

    public MovieListAdapterT(List <Movie> movieList) {
        this.movieList = movieList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_list, viewGroup, false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        context = viewGroup.getContext();
        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        final MovieViewHolder vh = (MovieViewHolder) viewHolder;
        vh.movieTitle.setText(movieList.get(i).getTitle());
        vh.movieRating.setText(String.valueOf(movieList.get(i).getRating()));

        if(movieList.get(i).getPoster() != "null"){
            Glide.with(context).load("https://image.tmdb.org/t/p/original"+movieList.get(i).getPoster()).into(vh.movieImage);
        }


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
