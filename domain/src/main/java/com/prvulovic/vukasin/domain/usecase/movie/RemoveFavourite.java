package com.prvulovic.vukasin.domain.usecase.movie;

import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.domain.entity.movie.MovieList;
import com.prvulovic.vukasin.domain.repository.movie.MovieRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class RemoveFavourite {

    private MovieRepository movieRepository;

    @Inject
    public RemoveFavourite(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Single<List<Movie>> remove(long movieId){
        return movieRepository.getMovie(movieId).flatMap(movie->{
            movie.setFavourite(false);
            return movieRepository.removeFavourite(movie).flatMap(flag->movieRepository.getFavouriteMovies());
        });
    }
}
