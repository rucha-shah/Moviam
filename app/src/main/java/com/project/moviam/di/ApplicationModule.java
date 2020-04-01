package com.project.moviam.di;


import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    Application application;
    public ApplicationModule(Application application)
    {
        this.application=application;
    }

    @Singleton
    @Provides
    @ApplicationContext
    Context provideContext(){
        return application;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return application;
    }
}
