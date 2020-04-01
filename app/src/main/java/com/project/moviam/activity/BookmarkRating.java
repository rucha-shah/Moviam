package com.project.moviam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.project.moviam.R;
import com.project.moviam.adapter.BookmarkAdapter;
import com.project.moviam.di.ViewModelFactory;
import com.project.moviam.repository.Bookmark;
import com.project.moviam.ui.bookmarkmovie.BookmarkViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookmarkRating extends AppCompatActivity implements BookmarkAdapter.BookmarkAdapterListener {
    String TAG = "Movie";
    BookmarkViewModel bookmarkViewModel;
    private RecyclerView recyclerView;
    private BookmarkAdapter bookmarkAdapter;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark_rating);

        recyclerView = findViewById(R.id.movie_list_recycler);

        bookmarkViewModel = ViewModelProviders.of(this,viewModelFactory).get(BookmarkViewModel.class);

        //RxJava implementation to view bookmarks based on ratings
        bookmarkViewModel.getBookmarkByRating().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Bookmark>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(List<Bookmark> bookmarks) {
                        Log.d(TAG, "onNext: ");
                        bookmarkAdapter = new BookmarkAdapter(bookmarks, BookmarkRating.this);
                        recyclerView.setLayoutManager(new GridLayoutManager(BookmarkRating.this, 2));
                        recyclerView.setAdapter(bookmarkAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
//        Observable<List<Bookmark>> listObservable = bookmarkViewModel.getBookmarkByRating();
//        Observer<List<Bookmark>> listObserver = new Observer<List<Bookmark>>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(List<Bookmark> bookmarks) {
//                Log.d(TAG, "onNext: " + bookmarks);
//                bookmarkAdapter = new BookmarkAdapter(bookmarks, BookmarkRating.this);
//                recyclerView.setLayoutManager(new GridLayoutManager(BookmarkRating.this, 2));
//                recyclerView.setAdapter(bookmarkAdapter);
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: "+e.getLocalizedMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        };
//        listObservable.subscribe(listObserver);
    }

    @Override
    public void onMovieClicked(Bookmark resultsItem) {
        if (resultsItem != null) {
            Intent intent = new Intent(this, MovieDescription.class);
            intent.putExtra("movie_title", resultsItem.getTitle());
            intent.putExtra("movie_rating", String.valueOf(resultsItem.getMovieRating()));
            intent.putExtra("movie_release_date", resultsItem.getMovieRelease());
            intent.putExtra("movie_overview", resultsItem.getOverview());
            intent.putExtra("movie_poster", resultsItem.getPosterPath());
            intent.putExtra("movie_cover", resultsItem.getCoverPath());
            intent.putExtra("movie_id", String.valueOf(resultsItem.getId()));
            startActivity(intent);

        } else {
            Toast.makeText(this, "Empty object", Toast.LENGTH_SHORT).show();
        }
    }
}
