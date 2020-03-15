package com.project.moviam.dao;

import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.project.moviam.repository.Bookmark;

import java.util.List;

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

//    default void insertOrUpdate(Bookmark bookmark)
//    {
//        Bookmark bookmarkObject=getBookmarkById(bookmark.getId());
//        if(bookmarkObject==null)
//        {
//            insert(bookmarkObject);
//        }
//        else
//        {
//            delete(bookmarkObject);
//        }
//    }


}
