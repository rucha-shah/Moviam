package com.project.moviam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.moviam.activity.BookmarkActivity;
import com.project.moviam.ui.popularmovie.PopularMovieFragment;

public class MainActivity extends AppCompatActivity {

    Button view_bookmark_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.popularmovie_container, PopularMovieFragment.newInstance())
                .commitNow();

        view_bookmark_button=findViewById(R.id.view_bookmark_button);
        view_bookmark_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, BookmarkActivity.class);
                startActivity(intent);
            }
        });

    }
}
