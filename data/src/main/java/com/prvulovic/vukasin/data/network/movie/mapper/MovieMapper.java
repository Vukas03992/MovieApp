package com.prvulovic.vukasin.data.network.movie.mapper;

import com.prvulovic.vukasin.data.network.movie.model.MovieListResponse;
import com.prvulovic.vukasin.data.network.movie.model.MovieResponse;
import com.prvulovic.vukasin.domain.entity.genre.Genre;
import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.domain.entity.movie.MovieList;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {

    public static MovieList movieListResponseToMovieList(MovieListResponse response){
        MovieList movieList=new MovieList();
        movieList.setPage(response.getPage());
        movieList.setPageCount(response.getPageCount());
        movieList.setMovieCount(response.getMovieCount());
        movieList.setMovieList(movieResponseArrayListToMovieArrayList(response.getMovieList()));
        return movieList;
    }

    public static List<Movie> movieResponseArrayListToMovieArrayList(List<MovieResponse> movieResponseList){
        List<Movie> movieList=new ArrayList<>();
        if (movieResponseList!=null && !movieResponseList.isEmpty()){
            for (MovieResponse movieResponse : movieResponseList) {
                movieList.add(movieResponseToMovie(movieResponse));
            }
        }
        return movieList;
    }

    public static Movie movieResponseToMovie(MovieResponse movieResponse){
        Movie movie=new Movie();
        movie.setId(movieResponse.getId());
        movie.setTitle(movieResponse.getTitle());
        movie.setReleaseDate(movieResponse.getReleaseDate());
        movie.setDescription(movieResponse.getDescription());
        movie.setPosterPath(movieResponse.getPosterPath());
        movie.setOriginalLanguage(movieResponse.getOriginalLanguage());
        movie.setOriginalTitle(movieResponse.getOriginalTitle());
        movie.setVoteCount(movieResponse.getVoteCount());
        movie.setVideo(movieResponse.isVideo());
        movie.setPopularity(movieResponse.getPopularity());
        movie.setVoteAverage(movieResponse.getVoteAverage());
        movie.setGenreList(genreIdsToGenreList(movieResponse.getGenreIdList()));
        movie.setBackdropPath(movieResponse.getBackdropPath());
        movie.setAdult(movieResponse.isAdult());
        return movie;
    }

    public static List<Genre> genreIdsToGenreList(List<Integer> genreIds){
        List<Genre> genres=new ArrayList<>();
        if (genreIds!=null && !genreIds.isEmpty()){
            for (Integer genreId : genreIds) {
                Genre genre=new Genre();
                genre.setId(genreId);
                genres.add(genre);
            }
        }
        return genres;
    }
}
