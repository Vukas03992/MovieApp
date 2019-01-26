package com.prvulovic.vukasin.domain.usecase.movie;

import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.domain.repository.movie.MovieRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class AddFavourite {

    private MovieRepository movieRepository;

    @Inject
    public AddFavourite(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Single<Boolean> add(Movie movie){
        movie.setFavourite(true);
        return movieRepository.addFavourite(movie);
    }
}
