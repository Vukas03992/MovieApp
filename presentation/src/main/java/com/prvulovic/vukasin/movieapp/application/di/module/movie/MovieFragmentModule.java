package com.prvulovic.vukasin.movieapp.application.di.module.movie;

import com.prvulovic.vukasin.movieapp.application.di.scope.FragmentScope;
import com.prvulovic.vukasin.movieapp.application.lifecycle.DisposableManager;
import com.prvulovic.vukasin.movieapp.application.lifecycle.FragmentLifecycleTask;
import com.prvulovic.vukasin.movieapp.movie.viewmodel.MovieViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public abstract class MovieFragmentModule {

    @Binds
    @IntoSet
    abstract FragmentLifecycleTask bindMovieViewModel(MovieViewModel viewModel);

    @Provides
    @FragmentScope
    static DisposableManager provideDisposableManager(){
        return new DisposableManager();
    }

}
