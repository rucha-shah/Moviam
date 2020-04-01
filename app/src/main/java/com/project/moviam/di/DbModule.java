package com.project.moviam.di;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.project.moviam.dao.BookmarkDao;
import com.project.moviam.repository.BookmarkDatabase;
import com.project.moviam.repository.BookmarkRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {
    @Provides
    @Singleton
    BookmarkDatabase provideDatabase(@NonNull Application application) {
        return Room.databaseBuilder(application,
                BookmarkDatabase.class, "bookmark.db")
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    BookmarkDao provideBookmarkDao(@NonNull BookmarkDatabase bookmarkDatabase) {
        return bookmarkDatabase.bookmarkDao();
    }

//    @Provides
//    BookmarkRepository provideBookmarkRepository(Application application,BookmarkRepository bookmarkRepository){
//        return bookmarkRepository;
//    }
}
