package com.prvulovic.vukasin.movieapp.application.base;

import android.app.Application;

import com.prvulovic.vukasin.movieapp.application.di.component.DaggerMovieAppComponent;
import com.prvulovic.vukasin.movieapp.application.di.component.MovieAppComponent;
import com.prvulovic.vukasin.movieapp.application.di.module.MovieAppModule;
import com.prvulovic.vukasin.movieapp.application.injector.ActivityInjector;

import javax.inject.Inject;

public class MovieApp extends Application {

    private MovieAppComponent component;

    @Inject ActivityInjector activityInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        component=DaggerMovieAppComponent.builder()
                .movieAppModule(new MovieAppModule(this))
                .build();
        component.inject(this);
    }

    public ActivityInjector getActivityInjector() {
        return activityInjector;
    }
}
