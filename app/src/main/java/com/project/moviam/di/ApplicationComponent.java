package com.project.moviam.di;

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

        }

)
public interface ApplicationComponent {
    void inject(MyApplication application);
}
