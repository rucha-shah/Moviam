package com.project.moviam.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Query;

import com.project.moviam.dao.BookmarkDao;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class BookmarkRepository {

    private BookmarkDao bookmarkDao;
    private LiveData<List<Bookmark>> allBookmarks;

    public BookmarkRepository(Application application) {
        BookmarkDatabase bookmarkDatabase=BookmarkDatabase.getInstance(application);
        bookmarkDao=bookmarkDatabase.bookmarkDao();
        allBookmarks=bookmarkDao.getAllBookmarkedMovies();
    }

    public void insert(Bookmark bookmark)
    {
        new InsertBookmarkAsyncTask(bookmarkDao).execute(bookmark);
    }

    public void delete(Bookmark bookmark)
    {
        new DeleteBookmarkAsyncTask(bookmarkDao).execute(bookmark);
    }

    public Bookmark findBookmarkById(Bookmark bookmark) throws ExecutionException, InterruptedException {
      return new FindBookmarkByIdAsyncTask(bookmarkDao).execute(bookmark).get();
    }


    public LiveData<List<Bookmark>> getAllBookmarks(){
        return allBookmarks;
    }

    private static class InsertBookmarkAsyncTask extends AsyncTask<Bookmark,Void,Void>{
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

    private static class DeleteBookmarkAsyncTask extends AsyncTask<Bookmark,Void,Void>{
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

    private static class FindBookmarkByIdAsyncTask extends AsyncTask<Bookmark,Bookmark,Bookmark>{
        private BookmarkDao bookmarkDao;

        public FindBookmarkByIdAsyncTask(BookmarkDao bookmarkDao) {
            this.bookmarkDao = bookmarkDao;
        }


        @Override
        protected Bookmark doInBackground(Bookmark... bookmarks) {
           return bookmarkDao.getBookmarkById(bookmarks[0].getId());
           //return null;
        }

//        @Override
//        protected void onPostExecute(Bookmark bookmark) {
//            myBookMark(bookmark);
//        }
    }
}
