package com.project.moviam.dao;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.moviam.repository.Bookmark;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface BookmarkDao {

    @Insert
    void insert(Bookmark bookmark);

    @Delete
    void delete(Bookmark bookmark);

    @Query("SELECT * from bookmark")
    LiveData<List<Bookmark>> getAllBookmarkedMovies();

    @Query("SELECT * from bookmark WHERE id= :id")
    Bookmark getBookmarkById(int id);

    @Query("SELECT * from bookmark ORDER BY movieRating DESC")
    Flowable<List<Bookmark>> getBookmarkByRating();

}
