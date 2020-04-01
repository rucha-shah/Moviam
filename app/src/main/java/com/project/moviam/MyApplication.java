package com.project.moviam;

import android.app.Application;

import com.project.moviam.di.ApplicationComponent;
import com.project.moviam.di.DaggerApplicationComponent;

public class MyApplication extends Application {

    //ApplicationComponent applicationComponent=
    //ApplicationComponent applicationComponent=
    ApplicationComponent applicationComponent= DaggerApplicationComponent.create();
}
