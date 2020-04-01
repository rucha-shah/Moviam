package com.project.moviam.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.project.moviam.di.ViewModelFactory;
import com.project.moviam.di.ViewModelKey;
import com.project.moviam.ui.bookmarkmovie.BookmarkViewModel;
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

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkViewModel.class)
    protected abstract ViewModel bookmarkViewModel(BookmarkViewModel bookmarkViewModel);
}
