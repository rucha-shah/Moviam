package com.project.moviam.di;

import android.app.Application;
import android.content.Context;

import com.project.moviam.MyApplication;
import com.project.moviam.retrofit.RetrofitInstanceModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(

        modules = {
                RetrofitInstanceModule.class,
                FragmentModule.class,
                ActivityModule.class,
                ViewModelModule.class,
                AndroidSupportInjectionModule.class,
                DbModule.class,
                ApplicationModule.class

        }

)
public interface ApplicationComponent {
    void inject(MyApplication application);

    @ApplicationContext
    Context getContext();

    Application getApplication();

}
