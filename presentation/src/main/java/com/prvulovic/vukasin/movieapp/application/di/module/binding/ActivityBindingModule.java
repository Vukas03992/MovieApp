package com.prvulovic.vukasin.movieapp.application.di.module.binding;

import android.app.Activity;

import com.prvulovic.vukasin.movieapp.application.di.component.movie.MovieActivityComponent;
import com.prvulovic.vukasin.movieapp.movie.activity.MovieActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MovieActivityComponent.class
})
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MovieActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMovieActivityInjector(MovieActivityComponent.Builder builder);
}
