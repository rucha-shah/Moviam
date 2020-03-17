package com.project.moviam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.project.moviam.activity.BookmarkActivity;
import com.project.moviam.activity.BookmarkRating;
import com.project.moviam.ui.popularmovie.PopularMovieFragment;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    Button view_bookmark_button;

    Button view_bookmark_rating_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.popularmovie_container, PopularMovieFragment.newInstance())
                .commitNow();

        Observable<Integer> integerObservable = Observable.just(1,2,3,4,5);
        Observer<Integer> integerObserver=new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("On Complete");
            }
        };
        integerObservable.subscribe(integerObserver);



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
