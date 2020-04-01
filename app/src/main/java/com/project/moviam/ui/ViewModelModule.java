package com.project.moviam.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.project.moviam.ui.popularmovie.PopularMovieViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory viewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(PopularMovieViewModel.class)
    protected abstract ViewModel popularMovieViewModel(PopularMovieViewModel popularMovieViewModel);
}
