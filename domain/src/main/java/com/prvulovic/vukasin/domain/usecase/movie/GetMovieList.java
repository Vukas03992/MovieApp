package com.prvulovic.vukasin.domain.usecase.movie;

import com.prvulovic.vukasin.domain.entity.genre.Genre;
import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.domain.entity.movie.MovieList;
import com.prvulovic.vukasin.domain.repository.genre.GenreRepository;
import com.prvulovic.vukasin.domain.repository.movie.MovieRepository;
import java.util.Map;


import javax.inject.Inject;

import io.reactivex.Single;

public class GetMovieList {

    private MovieRepository movieRepository;
    private GenreRepository genreRepository;

    @Inject
    public GetMovieList(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public Single<MovieList> get(int page){
        return genreRepository.getGenreIdNameMap().flatMap(genres -> movieRepository.getMovieList(page).map(movieList -> setGenreName(movieList,genres)));
    }

    private MovieList setGenreName(MovieList movieList, Map<Long,String> genreMap){
        for (Movie movie : movieList.getMovieList()) {
            for (Genre genre : movie.getGenreList()) {
                genre.setName(genreMap.get(genre.getId()));
            }
        }
        return movieList;
    }
}
