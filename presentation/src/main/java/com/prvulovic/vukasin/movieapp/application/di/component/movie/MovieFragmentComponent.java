package com.prvulovic.vukasin.movieapp.application.di.component.movie;

import com.prvulovic.vukasin.movieapp.application.di.module.movie.MovieFragmentModule;
import com.prvulovic.vukasin.movieapp.application.di.scope.FragmentScope;
import com.prvulovic.vukasin.movieapp.movie.fragment.MovieFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@FragmentScope
@Subcomponent(modules = {
        MovieFragmentModule.class
})
public interface MovieFragmentComponent extends AndroidInjector<MovieFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MovieFragment>{

    }
}
