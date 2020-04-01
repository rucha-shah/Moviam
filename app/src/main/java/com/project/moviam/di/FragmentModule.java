package com.project.moviam.di;

import com.project.moviam.ui.popularmovie.PopularMovieFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract PopularMovieFragment contributePopularMovieFragment();
}
