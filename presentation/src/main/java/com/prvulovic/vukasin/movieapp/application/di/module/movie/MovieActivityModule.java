package com.prvulovic.vukasin.movieapp.application.di.module.movie;

import com.prvulovic.vukasin.movieapp.application.base.ScreenNavigator;
import com.prvulovic.vukasin.movieapp.application.di.scope.ActivityScope;
import com.prvulovic.vukasin.movieapp.application.lifecycle.ActivityLifecycleTask;
import com.prvulovic.vukasin.movieapp.movie.MovieNavigator;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public abstract class MovieActivityModule {

    @Binds
    @IntoSet
    abstract ActivityLifecycleTask bindMovieNavigator(MovieNavigator navigator);

    @Provides
    @ActivityScope
    static ScreenNavigator provideScreenNavigator(MovieNavigator navigator){
        return navigator;
    }
}
