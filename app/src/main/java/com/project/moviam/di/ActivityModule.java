package com.project.moviam.di;

import com.project.moviam.MainActivity;
import com.project.moviam.activity.BookmarkActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    abstract BookmarkActivity contributeBookmarkActivity();
}
