package com.project.moviam.di;

import com.project.moviam.MainActivity;
import com.project.moviam.activity.BookmarkActivity;
import com.project.moviam.activity.BookmarkRating;
import com.project.moviam.activity.MovieDescription;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    abstract BookmarkActivity contributeBookmarkActivity();

    @ContributesAndroidInjector()
    abstract BookmarkRating contributeBookmarkRating();

    @ContributesAndroidInjector()
    abstract MovieDescription contributeMovieDescription();
}
