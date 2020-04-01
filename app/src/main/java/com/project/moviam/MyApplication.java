package com.project.moviam;

import android.app.Activity;
import android.app.Application;

import com.project.moviam.di.ApplicationComponent;
//import com.project.moviam.di.DaggerApplicationComponent;

import javax.inject.Inject;

import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        DaggerApplicationComponent.builder()
//                .build()
//                .inject(this);
    }
}
