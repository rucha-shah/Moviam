package com.project.moviam;

import android.app.Application;

import com.project.moviam.di.ApplicationComponent;
import com.project.moviam.di.DaggerApplicationComponent;

public class MyApplication extends Application {

    ApplicationComponent applicationComponent= DaggerApplicationComponent.create();
    //ApplicationComponent applicationComponent=
}
