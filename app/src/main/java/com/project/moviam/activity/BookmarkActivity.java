package com.project.moviam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.project.moviam.R;
import com.project.moviam.adapter.BookmarkAdapter;
import com.project.moviam.data.ResultsItem;
import com.project.moviam.repository.Bookmark;
import com.project.moviam.ui.bookmarkmovie.BookmarkViewModel;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class BookmarkActivity extends AppCompatActivity implements BookmarkAdapter.BookmarkAdapterListener {
    BookmarkViewModel bookmarkViewModel;
    private RecyclerView recyclerView;
    private BookmarkAdapter bookmarkAdapter;
    String TAG = "Movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        recyclerView = findViewById(R.id.movie_list_recycler);

        bookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);
        //View all Bookmarks
//        bookmarkViewModel.getAllBookmarks().observe(this, new Observer<List<Bookmark>>() {
//            @Override
//            public void onChanged(List<Bookmark> bookmarks) {
//                Log.d(TAG, "onChanged: Bookmark");
//                bookmarkAdapter = new BookmarkAdapter(bookmarks,BookmarkActivity.this);
//                recyclerView.setLayoutManager(new GridLayoutManager(BookmarkActivity.this, 2));
//                recyclerView.setAdapter(bookmarkAdapter);
//
//            }
//        });


//RxJava implementation to view bookmarks based on ratings
        bookmarkViewModel.getBookmarkByRating().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new FlowableSubscriber<List<Bookmark>>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        Log.d(TAG, "onSubscribe:" + s);
                    }

                    @Override
                    public void onNext(List<Bookmark> bookmarks) {
                        Log.d(TAG, "onNext: " + bookmarks);
                        bookmarkAdapter = new BookmarkAdapter(bookmarks, BookmarkActivity.this);
                        recyclerView.setLayoutManager(new GridLayoutManager(BookmarkActivity.this, 2));
                        recyclerView.setAdapter(bookmarkAdapter);

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d(TAG, "onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: ");
                    }
                });
    }

    @Override
    public void onMovieClicked(Bookmark resultsItem) {
        if (resultsItem != null) {
            Intent intent = new Intent(this, MovieDescription.class);
            intent.putExtra("movie_title", resultsItem.getTitle());
            intent.putExtra("movie_rating", String.valueOf(resultsItem.getMovieRating()));
            //intent.putExtra("movie_release_date", resultsItem.ge());
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
