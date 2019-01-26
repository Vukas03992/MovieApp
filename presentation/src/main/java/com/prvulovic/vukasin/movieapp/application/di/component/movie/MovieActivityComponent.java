package com.prvulovic.vukasin.movieapp.application.di.component.movie;

import com.prvulovic.vukasin.movieapp.application.di.module.binding.movie.MovieFragmentBindingModule;
import com.prvulovic.vukasin.movieapp.application.di.module.movie.MovieActivityModule;
import com.prvulovic.vukasin.movieapp.application.di.scope.ActivityScope;
import com.prvulovic.vukasin.movieapp.movie.activity.MovieActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        MovieActivityModule.class,
        MovieFragmentBindingModule.class
})
public interface MovieActivityComponent extends AndroidInjector<MovieActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MovieActivity>{

    }
}
