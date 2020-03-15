package com.project.moviam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;

import android.os.Bundle;
import android.util.Log;

import com.project.moviam.R;
import com.project.moviam.adapter.BookmarkAdapter;
import com.project.moviam.repository.Bookmark;
import com.project.moviam.repository.BookmarkDatabase;
import com.project.moviam.repository.BookmarkRepository;
import com.project.moviam.ui.bookmarkmovie.BookmarkViewModel;

import java.util.List;

import javax.sql.DataSource;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import rx.schedulers.Schedulers;

public class BookmarkActivity extends AppCompatActivity {
    BookmarkViewModel bookmarkViewModel;
    private RecyclerView recyclerView;
    private BookmarkAdapter bookmarkAdapter;
    String TAG="Movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        recyclerView = findViewById(R.id.movie_list_recycler);

        bookmarkViewModel = ViewModelProviders.of(this).get(BookmarkViewModel.class);
        bookmarkViewModel.getAllBookmarks().observe(this, new Observer<List<Bookmark>>() {
            @Override
            public void onChanged(List<Bookmark> bookmarks) {
                Log.d(TAG, "onChanged: Bookmark");
                bookmarkAdapter = new BookmarkAdapter(bookmarks);
                recyclerView.setLayoutManager(new GridLayoutManager(BookmarkActivity.this, 2));
                recyclerView.setAdapter(bookmarkAdapter);

            }
        });

        Observable observable=Observable.create(new ObservableOnSubscribe<Bookmark>() {
            @Override
            public void subscribe(ObservableEmitter<Bookmark> emitter) throws Exception {

            }
        });

//        Observable<Bookmark> bookmarkObservable =Observable.fromIterable(BookmarkRepository.getAllBookmarks())
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//
//        bookmarkObservable.subscribe(new io.reactivex.Observer<Bookmark>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(Bookmark bookmark) {
//                Log.d(TAG, "onNext: "+Thread.currentThread().getName());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.e(TAG, "onError: ",e );
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        });
//    }
    }
}
