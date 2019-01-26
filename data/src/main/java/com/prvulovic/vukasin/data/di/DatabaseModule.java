package com.prvulovic.vukasin.data.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.prvulovic.vukasin.data.database.AppDatabase;
import com.prvulovic.vukasin.data.database.movie.dao.MovieDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(Context context){
        return Room.databaseBuilder(context,AppDatabase.class,"movie_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(AppDatabase appDatabase){
        return appDatabase.getMovieDao();
    }
}
