package com.project.moviam.ui.bookmarkmovie;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.project.moviam.dao.BookmarkDao;
import com.project.moviam.repository.Bookmark;
import com.project.moviam.repository.BookmarkDatabase;
import com.project.moviam.repository.BookmarkRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import io.reactivex.Flowable;

public class BookmarkViewModel extends AndroidViewModel {
    private BookmarkRepository repository;
    private LiveData<List<Bookmark>> allBookmarks;

    private Flowable<List<Bookmark>> listFlowable;

    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        repository = new BookmarkRepository(application);
        allBookmarks = repository.getAllBookmarks();
        //bookmarkDatabase = BookmarkDatabase.getInstance(application);
        listFlowable = repository.getBookmarkByRating();
    }

    public void insert(Bookmark bookmark) {
        repository.insert(bookmark);
    }

    public void delete(Bookmark bookmark) {
        repository.delete(bookmark);
    }

    public Bookmark findBookmarkById(Bookmark bookmark) {
        Bookmark bookmarkObject = null;
        try {
            bookmarkObject = repository.findBookmarkById(bookmark);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return bookmarkObject;
    }

    public LiveData<List<Bookmark>> getAllBookmarks() {
        return allBookmarks;
    }

    public Flowable<List<Bookmark>> getBookmarkByRating() {
        return listFlowable;
    }
}
