package com.prvulovic.vukasin.movieapp.application.di.component.movie;

import com.prvulovic.vukasin.movieapp.application.di.module.movie.MovieListFragmentModule;
import com.prvulovic.vukasin.movieapp.application.di.scope.FragmentScope;
import com.prvulovic.vukasin.movieapp.movie.fragment.MovieListFragment;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@FragmentScope
@Subcomponent(modules = {
        MovieListFragmentModule.class
})
public interface MovieListFragmentComponent extends AndroidInjector<MovieListFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MovieListFragment>{

    }
}
