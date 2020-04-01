package com.project.moviam.di;

import com.project.moviam.MainActivity;
import com.project.moviam.retrofit.RetrofitInstance;
import com.project.moviam.retrofit.RetrofitInstanceModule;
import com.project.moviam.ui.ActivityModule;
import com.project.moviam.ui.FragmentModule;
import com.project.moviam.ui.ViewModelModule;
import com.project.moviam.ui.popularmovie.PopularMovieFragment;
import com.project.moviam.ui.popularmovie.PopularMovieViewModel;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;

@Component(

        modules = {
                RetrofitInstanceModule.class,
                FragmentModule.class,
                ActivityModule.class,
                ViewModelModule.class,
               // AndroidSupportInjectionModule.class

        }

)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(PopularMovieFragment popularMovieFragment);
    void inject(PopularMovieViewModel popularMovieViewModel);
}
