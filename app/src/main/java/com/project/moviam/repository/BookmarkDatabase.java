package com.project.moviam.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.project.moviam.dao.BookmarkDao;
import com.project.moviam.data.DateConverter;


@Database(entities = Bookmark.class, version = 1, exportSchema = false)

public abstract class BookmarkDatabase extends RoomDatabase {

    private static BookmarkDatabase instance;

    public abstract BookmarkDao bookmarkDao();

    public static synchronized BookmarkDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BookmarkDatabase.class, "bookmark").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            //new PopulateDbAsyncTask(instance).execute();

        }
    };

//    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
//        private  BookmarkDao bookmarkDao;
//
//        public PopulateDbAsyncTask(BookmarkDatabase db) {
//            bookmarkDao = db.bookmarkDao();
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            //bookmarkDao.insert(new Bookmark(0,"a","a","https://image.tmdb.org/t/p/w185/bB42KDdfWkOvmzmYkmK58ZlCa9P.jpg","https://image.tmdb.org/t/p/w185/bB42KDdfWkOvmzmYkmK58ZlCa9P.jpg",0));
//            return null;
//        }
//    }

}
