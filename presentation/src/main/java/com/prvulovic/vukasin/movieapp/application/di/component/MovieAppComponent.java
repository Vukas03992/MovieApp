package com.prvulovic.vukasin.movieapp.application.di.component;

import com.prvulovic.vukasin.movieapp.application.base.MovieApp;
import com.prvulovic.vukasin.movieapp.application.di.module.MovieAppModule;
import com.prvulovic.vukasin.movieapp.application.di.module.binding.ActivityBindingModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        MovieAppModule.class,
        ActivityBindingModule.class
})
public interface MovieAppComponent {

    void inject(MovieApp movieApp);
}
