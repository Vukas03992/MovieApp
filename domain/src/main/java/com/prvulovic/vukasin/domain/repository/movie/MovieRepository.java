package com.prvulovic.vukasin.domain.repository.movie;

import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.domain.entity.movie.MovieList;

import java.util.List;

import io.reactivex.Single;

public interface MovieRepository {

    Single<MovieList> getMovieList(int page);

    Single<Movie> getMovie(long movieId);

    Single<Boolean> addFavourite(Movie movie);

    Single<List<Movie>> getFavouriteMovies();

    Single<Boolean> removeFavourite(Movie movie);
}
