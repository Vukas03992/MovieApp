package com.prvulovic.vukasin.movieapp.application.di.module.movie;

import com.prvulovic.vukasin.movieapp.application.di.scope.FragmentScope;
import com.prvulovic.vukasin.movieapp.application.lifecycle.DisposableManager;
import com.prvulovic.vukasin.movieapp.application.lifecycle.FragmentLifecycleTask;
import com.prvulovic.vukasin.movieapp.movie.viewmodel.MovieListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public abstract class MovieListFragmentModule {

    @Binds
    @IntoSet
    abstract FragmentLifecycleTask bindMovieListViewModel(MovieListViewModel viewModel);

    @Provides
    @FragmentScope
    static DisposableManager provideDisposableManager(){
        return new DisposableManager();
    }
}
