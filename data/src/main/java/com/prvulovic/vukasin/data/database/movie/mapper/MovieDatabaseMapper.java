package com.prvulovic.vukasin.data.database.movie.mapper;

import com.prvulovic.vukasin.data.database.movie.model.MovieData;
import com.prvulovic.vukasin.domain.entity.genre.Genre;
import com.prvulovic.vukasin.domain.entity.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDatabaseMapper {

    public static List<Movie> movieDataListToMovieList(List<MovieData> movieDataList){
        List<Movie> movieList=new ArrayList<>();
        if (movieDataList!=null && !movieDataList.isEmpty()){
            for (MovieData movieData : movieDataList) {
                Movie movie=new Movie();
                movie.setId(movieData.getId());
                movie.setTitle(movieData.getTitle());
                movie.setReleaseDate(movieData.getReleaseDate());
                movie.setDescription(movieData.getDescription());
                movie.setPosterPath(movieData.getPosterPath());
                movie.setOriginalLanguage(movieData.getOriginalLanguage());
                movie.setOriginalTitle(movieData.getOriginalTitle());
                movie.setVoteCount(movieData.getVoteCount());
                movie.setVideo(movieData.isVideo());
                movie.setPopularity(movieData.getPopularity());
                movie.setVoteAverage(movieData.getVoteAverage());
                movie.setBackdropPath(movieData.getBackdropPath());
                movie.setAdult(movieData.isAdult());
                movie.setFavourite(movieData.isFavourite());
                movie.setGenreList(getGenreList(movieData.getGenresNames(),movieData.getGenresIds()));
                movieList.add(movie);
            }
        }
        return movieList;
    }

    public static List<MovieData> movieListToMovieDataList(List<Movie> movieList){
        List<MovieData> movieDataList=new ArrayList<>();
        if (movieList!=null && !movieList.isEmpty()){
            for (Movie movie : movieList) {
                movieDataList.add(movieToMovieData(movie));
            }
        }
        return movieDataList;
    }

    public static MovieData movieToMovieData(Movie movie){
        MovieData movieData=new MovieData();
        movieData.setId(movie.getId());
        movieData.setTitle(movie.getTitle());
        movieData.setReleaseDate(movie.getReleaseDate());
        movieData.setDescription(movie.getDescription());
        movieData.setPosterPath(movie.getPosterPath());
        movieData.setOriginalLanguage(movie.getOriginalLanguage());
        movieData.setOriginalTitle(movie.getOriginalTitle());
        movieData.setVoteCount(movie.getVoteCount());
        movieData.setVideo(movie.isVideo());
        movieData.setPopularity(movie.getPopularity());
        movieData.setVoteAverage(movie.getVoteAverage());
        movieData.setBackdropPath(movie.getBackdropPath());
        movieData.setAdult(movie.isAdult());
        movieData.setFavourite(movie.isFavourite());
        movieData.setGenresNames(saveGenresNames(movie.getGenreList()));
        movieData.setGenresIds(saveGenresIds(movie.getGenreList()));
        return movieData;
    }

    private static String saveGenresNames(List<Genre> genreList){
        StringBuilder builder=new StringBuilder();
        if (genreList!=null && !genreList.isEmpty()){
            for (Genre genre : genreList) {
                builder.append(genre.getName()).append(" ");
            }
        }
        return builder.toString();
    }

    private static String saveGenresIds(List<Genre> genreList){
        StringBuilder builder=new StringBuilder();
        if (genreList!=null && !genreList.isEmpty()){
            for (Genre genre : genreList) {
                builder.append(genre.getId()).append(" ");
            }
        }
        return builder.toString();
    }

    private static List<Genre> getGenreList(String names, String ids){
        List<Genre> genres=new ArrayList<>();
        String[] namesArray=names.split(" ");
        String[] idsArray=ids.split(" ");
        if (namesArray.length==idsArray.length){
            for (int i = 0; i < namesArray.length; i++) {
                Genre genre=new Genre();
                genre.setName(namesArray[i]);
                genre.setId(Long.parseLong(idsArray[i]));
                genres.add(genre);
            }
        }
        return genres;
    }
}
