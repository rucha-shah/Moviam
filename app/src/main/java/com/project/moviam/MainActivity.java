package com.project.moviam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.project.moviam.activity.BookmarkActivity;
import com.project.moviam.activity.BookmarkRating;
import com.project.moviam.di.ApplicationComponent;
import com.project.moviam.ui.popularmovie.PopularMovieFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    Button view_bookmark_button;
    Button view_bookmark_rating_button;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.popularmovie_container, PopularMovieFragment.newInstance())
                .commitNow();



        view_bookmark_button = findViewById(R.id.view_bookmark_button);
        view_bookmark_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookmarkActivity.class);
                startActivity(intent);
            }
        });

        view_bookmark_rating_button=findViewById(R.id.view_bookmark_rating_button);
        view_bookmark_rating_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BookmarkRating.class);
                startActivity(intent);

            }
        });

    }
}
