package com.prvulovic.vukasin.domain.usecase.movie;

import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.domain.repository.movie.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetFavouriteMovies {

    private MovieRepository movieRepository;

    @Inject
    public GetFavouriteMovies(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Single<List<Movie>> get(){
        return movieRepository.getFavouriteMovies();
    }
}
