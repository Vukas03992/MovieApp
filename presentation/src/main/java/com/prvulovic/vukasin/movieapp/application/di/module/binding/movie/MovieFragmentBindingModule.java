package com.prvulovic.vukasin.movieapp.application.di.module.binding.movie;

import android.support.v4.app.Fragment;

import com.prvulovic.vukasin.movieapp.application.di.component.movie.MovieFragmentComponent;
import com.prvulovic.vukasin.movieapp.application.di.component.movie.MovieListFragmentComponent;
import com.prvulovic.vukasin.movieapp.movie.fragment.MovieFragment;
import com.prvulovic.vukasin.movieapp.movie.fragment.MovieListFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        MovieListFragmentComponent.class,
        MovieFragmentComponent.class
})
public abstract class MovieFragmentBindingModule {

    @Binds
    @IntoMap
    @FragmentKey(MovieListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMovieListFragmentAndroidInjector(MovieListFragmentComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(MovieFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindMovieFragmentAndroidInjector(MovieFragmentComponent.Builder builder);

}

