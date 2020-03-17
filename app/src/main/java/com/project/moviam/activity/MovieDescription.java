package com.project.moviam.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.moviam.R;
import com.project.moviam.repository.Bookmark;
import com.project.moviam.ui.bookmarkmovie.BookmarkViewModel;
import com.squareup.picasso.Picasso;

import java.util.Date;

import io.reactivex.Observable;


public class MovieDescription extends AppCompatActivity implements Animation.AnimationListener {

    ImageView moviePoster;
    ImageView movieCover;
    TextView movieTitle;
    TextView movieOverview;
    TextView movieReleaseDate;
    TextView movieRating;
    ImageButton bookmarkButton;
    Boolean bookmarkFlag;
    Animation animFadein;
    BookmarkViewModel bookmarkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_description);

        displayMovieDescription();
    }

    void displayMovieDescription() {
        Intent intent = getIntent();
        String movie_title = intent.getStringExtra("movie_title");
        String movie_rating = intent.getStringExtra("movie_rating");
        String movie_release_date = intent.getStringExtra("movie_release_date");
        String movie_overview = intent.getStringExtra("movie_overview");
        String movie_poster = intent.getStringExtra("movie_poster");
        String movie_cover = intent.getStringExtra("movie_cover");
        String baseImageUrl = "https://image.tmdb.org/t/p/w185";
        String movie_id = intent.getStringExtra("movie_id");

        moviePoster = findViewById(R.id.movie_description_image);
        movieCover = findViewById(R.id.movie_cover_image);
        movieTitle = findViewById(R.id.movie_title);
        movieOverview = findViewById(R.id.movie_overview);
        movieReleaseDate = findViewById(R.id.movie_release_date);
        movieRating = findViewById(R.id.movie_vote_average);
        bookmarkButton = findViewById(R.id.bookmark_button);

        // load the animation
        animFadein = AnimationUtils.loadAnimation(this,
                R.anim.move);

        // set animation listener
        animFadein.setAnimationListener(this);

        Picasso.with(movieCover.getContext()).load(baseImageUrl + movie_cover).fit().into(movieCover);
        Picasso.with(moviePoster.getContext()).load(baseImageUrl + movie_poster).fit().into(moviePoster);
        Log.d("Movie", "displayMovieDescription: " + this);
        movieTitle.setText(movie_title);
        movieRating.setText(movie_rating);
        if (movie_release_date != null)
            movieReleaseDate.setText("Release Date: " + movie_release_date);
        else
            movieReleaseDate.setText("");
        movieOverview.setText(movie_overview);

        moviePoster.startAnimation(animFadein);
        movieCover.startAnimation(animFadein);
        movieTitle.startAnimation(animFadein);
        movieRating.startAnimation(animFadein);
        movieReleaseDate.startAnimation(animFadein);
        movieOverview.startAnimation(animFadein);

        bookmarkViewModel = new BookmarkViewModel(getApplication());
        Bookmark bookmark = new Bookmark(Integer.parseInt(movie_id), movie_title, movie_overview, movie_poster, movie_cover, Double.parseDouble(movie_rating), movie_release_date);

        if (bookmarkViewModel.findBookmarkById(bookmark) != null) {
            bookmarkButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBookmark)));
        } else {
            bookmarkButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlack)));
        }


        bookmarkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("Movie", "onClick:" + bookmarkViewModel.findBookmarkById(bookmark));
                if (bookmarkViewModel.findBookmarkById(bookmark) != null) {
                    bookmarkFlag = false;
                    bookmarkButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBlack)));
                    Toast.makeText(getApplicationContext(), "Removed from bookmark", Toast.LENGTH_SHORT).show();
                    bookmarkViewModel.delete(bookmark);
                } else {
                    bookmarkFlag = true;
                    bookmarkButton.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorBookmark)));
                    Toast.makeText(getApplicationContext(), "Added to bookmark", Toast.LENGTH_SHORT).show();
                    bookmarkViewModel.insert(bookmark);

                }
            }
        });

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
