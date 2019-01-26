package com.prvulovic.vukasin.data.database.movie.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.prvulovic.vukasin.data.database.movie.model.MovieData;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    Single<List<MovieData>> getAllMovies();

    @Query("SELECT * FROM movie")
    List<MovieData> getMovies();

    @Insert
    void insertAll(MovieData[] movieData);

    @Update
    void update(MovieData[] movieData);

    @Delete
    void delete(MovieData movieData);

}
