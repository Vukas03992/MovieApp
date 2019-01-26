package com.prvulovic.vukasin.data.database.movie.manager;

import com.prvulovic.vukasin.data.database.movie.dao.MovieDao;
import com.prvulovic.vukasin.data.database.movie.mapper.MovieDatabaseMapper;
import com.prvulovic.vukasin.data.database.movie.model.MovieData;
import com.prvulovic.vukasin.domain.entity.movie.Movie;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class MovieDatabaseManager {

    @Inject MovieDao movieDao;

    @Inject
    public MovieDatabaseManager() {
    }

    public Single<List<Movie>> getMovieList(){
        return movieDao.getAllMovies().map(MovieDatabaseMapper::movieDataListToMovieList);
    }

    public void saveMovieList(List<Movie> movies){
        if (movies.size()==movieDao.getMovies().size())return;
        movieDao.insertAll(MovieDatabaseMapper.movieListToMovieDataList(movies).toArray(new MovieData[movies.size()]));
    }

    public void updateMovie(Movie movie){
        MovieData movieData=MovieDatabaseMapper.movieToMovieData(movie);
        movieDao.update(new MovieData[]{movieData});
    }
}
