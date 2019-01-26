package com.prvulovic.vukasin.data.di;

import com.prvulovic.vukasin.data.manager.cast.CastManager;
import com.prvulovic.vukasin.data.manager.genre.GenreManager;
import com.prvulovic.vukasin.data.manager.movie.MovieManager;
import com.prvulovic.vukasin.domain.repository.cast.CastRepository;
import com.prvulovic.vukasin.domain.repository.genre.GenreRepository;
import com.prvulovic.vukasin.domain.repository.movie.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    MovieRepository provideMovieRepository(MovieManager manager){
        return manager;
    }

    @Provides
    @Singleton
    GenreRepository provideGenreRepository(GenreManager manager){
        return manager;
    }

    @Provides
    @Singleton
    CastRepository provideCastRepository(CastManager manager){
        return manager;
    }
}
