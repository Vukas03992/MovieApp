package com.prvulovic.vukasin.movieapp.application.di.module;

import android.content.Context;

import com.prvulovic.vukasin.data.di.DataModule;
import com.prvulovic.vukasin.movieapp.application.base.MovieApp;
import com.prvulovic.vukasin.movieapp.application.executor.JobExecutor;
import com.prvulovic.vukasin.movieapp.application.executor.ThreadExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        DataModule.class
})
public class MovieAppModule {

    private MovieApp app;

    public MovieAppModule(MovieApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor){
        return jobExecutor;
    }

    @Provides
    @Singleton
    Context provideContext(){
        return app;
    }

}
