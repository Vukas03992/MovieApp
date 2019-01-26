package com.prvulovic.vukasin.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.prvulovic.vukasin.data.database.movie.dao.MovieDao;
import com.prvulovic.vukasin.data.database.movie.model.MovieData;

@Database(entities = {MovieData.class}, version=1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();
}
