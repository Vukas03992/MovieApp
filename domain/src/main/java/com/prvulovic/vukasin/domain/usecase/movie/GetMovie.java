package com.prvulovic.vukasin.domain.usecase.movie;

import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.domain.repository.cast.CastRepository;
import com.prvulovic.vukasin.domain.repository.movie.MovieRepository;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetMovie {

    private MovieRepository movieRepository;
    private CastRepository castRepository;

    @Inject
    public GetMovie(MovieRepository movieRepository, CastRepository castRepository) {
        this.movieRepository = movieRepository;
        this.castRepository = castRepository;
    }

    public Single<Movie> get(long movieId){
        return castRepository.getCast(movieId).flatMap(cast-> movieRepository.getMovie(movieId).map(movie -> {
            movie.setCast(cast);
            return movie;
        }));
    }
}
