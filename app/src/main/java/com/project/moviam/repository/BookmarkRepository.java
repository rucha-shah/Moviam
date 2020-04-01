package com.project.moviam.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.project.moviam.dao.BookmarkDao;
import com.project.moviam.di.ApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Flowable;
import io.reactivex.Observable;


public class BookmarkRepository {

    private BookmarkDao bookmarkDao;
    private LiveData<List<Bookmark>> allBookmarks;
    private Observable<List<Bookmark>> listObservable;

    @Inject
    public BookmarkRepository(Application application) {
        BookmarkDatabase bookmarkDatabase = BookmarkDatabase.getInstance(application);
        bookmarkDao = bookmarkDatabase.bookmarkDao();
        allBookmarks = bookmarkDao.getAllBookmarkedMovies();
        listObservable = bookmarkDao.getBookmarkByRating();
    }

    public void insert(Bookmark bookmark) {
        new InsertBookmarkAsyncTask(bookmarkDao).execute(bookmark);
    }

    public void delete(Bookmark bookmark) {
        new DeleteBookmarkAsyncTask(bookmarkDao).execute(bookmark);
    }

    public Bookmark findBookmarkById(Bookmark bookmark) throws ExecutionException, InterruptedException {
        return new FindBookmarkByIdAsyncTask(bookmarkDao).execute(bookmark).get();
    }


    public LiveData<List<Bookmark>> getAllBookmarks() {
        return allBookmarks;
    }

    public Observable<List<Bookmark>> getBookmarkByRating() {
        return listObservable;
    }

    private static class InsertBookmarkAsyncTask extends AsyncTask<Bookmark, Void, Void> {
        private BookmarkDao bookmarkDao;

        public InsertBookmarkAsyncTask(BookmarkDao bookmarkDao) {
            this.bookmarkDao = bookmarkDao;
        }

        @Override
        protected Void doInBackground(Bookmark... bookmarks) {
            bookmarkDao.insert(bookmarks[0]);
            return null;
        }
    }

    private static class DeleteBookmarkAsyncTask extends AsyncTask<Bookmark, Void, Void> {
        private BookmarkDao bookmarkDao;

        public DeleteBookmarkAsyncTask(BookmarkDao bookmarkDao) {
            this.bookmarkDao = bookmarkDao;
        }

        @Override
        protected Void doInBackground(Bookmark... bookmarks) {
            bookmarkDao.delete(bookmarks[0]);
            return null;
        }
    }

    private static class FindBookmarkByIdAsyncTask extends AsyncTask<Bookmark, Bookmark, Bookmark> {
        private BookmarkDao bookmarkDao;

        public FindBookmarkByIdAsyncTask(BookmarkDao bookmarkDao) {
            this.bookmarkDao = bookmarkDao;
        }


        @Override
        protected Bookmark doInBackground(Bookmark... bookmarks) {
            return bookmarkDao.getBookmarkById(bookmarks[0].getId());
        }
    }


}
