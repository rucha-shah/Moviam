package com.project.moviam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.moviam.R;
import com.project.moviam.data.MovieResponse;
import com.project.moviam.data.ResultsItem;
import com.project.moviam.ui.popularmovie.PopularMovieFragment;
import com.squareup.picasso.Picasso;


public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesHolder> implements Animation.AnimationListener {

    MovieResponse movieResponse;
    String baseImageUrl = "https://image.tmdb.org/t/p/w185";
    PopularMovieAdapterListener popularMovieAdapterListener;
    Animation animFadein;

    public static class PopularMoviesHolder extends RecyclerView.ViewHolder {
        ImageView movieImage;

        public PopularMoviesHolder(@NonNull View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_poster_image);
        }
    }

    public PopularMoviesAdapter(MovieResponse movieResponse, PopularMovieAdapterListener popularMovieAdapterListener) {
        this.movieResponse = movieResponse;
        this.popularMovieAdapterListener = popularMovieAdapterListener;

    }

    @NonNull
    @Override
    public PopularMoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_recycler_items, parent, false);
        PopularMoviesHolder popularMoviesHolder = new PopularMoviesHolder(view);
        // load the animation
        animFadein = AnimationUtils.loadAnimation(parent.getContext(),
                R.anim.fade_in);

        // set animation listener
        animFadein.setAnimationListener(this);
        return popularMoviesHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PopularMoviesHolder holder, int position) {
        holder.movieImage.startAnimation(animFadein);

        Picasso.with(holder.movieImage.getContext()).load(baseImageUrl + movieResponse.getResults().get(position).getPosterPath()).resize(650, 1050).into(holder.movieImage);
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popularMovieAdapterListener.onMovieClicked(movieResponse.getResults().get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        if (movieResponse != null)
            return movieResponse.getResults().size();
        else
            return 0;
    }

    public interface PopularMovieAdapterListener {
        void onMovieClicked(ResultsItem resultsItem);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
