package com.project.moviam.di;

import com.project.moviam.MainActivity;
import com.project.moviam.retrofit.RetrofitInstance;
import com.project.moviam.ui.popularmovie.PopularMovieFragment;

import dagger.Component;

@Component(

        modules = {RetrofitInstance.class,}

)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(PopularMovieFragment popularMovieFragment);
}
