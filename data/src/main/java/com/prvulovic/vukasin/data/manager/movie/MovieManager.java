package com.prvulovic.vukasin.data.manager.movie;

import com.prvulovic.vukasin.data.database.movie.manager.MovieDatabaseManager;
import com.prvulovic.vukasin.data.database.movie.mapper.MovieDatabaseMapper;
import com.prvulovic.vukasin.data.database.movie.model.MovieData;
import com.prvulovic.vukasin.data.network.movie.manager.MovieNetwork;
import com.prvulovic.vukasin.data.network.state.NetworkStateManager;
import com.prvulovic.vukasin.data.persistence.repository.AuthenticationRepository;
import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.domain.entity.movie.MovieList;
import com.prvulovic.vukasin.domain.repository.movie.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class MovieManager implements MovieRepository {

    @Inject MovieNetwork movieNetwork;
    @Inject AuthenticationRepository authenticationRepository;
    @Inject MovieDatabaseManager databaseManager;
    @Inject NetworkStateManager networkStateManager;

    private MovieList movieList;

    @Inject public MovieManager() { }

    @Override
    public Single<MovieList> getMovieList(int page) {
        if (networkStateManager.getConnection()) {
            return movieNetwork.get(authenticationRepository.getApiKey(), page).map(this::saveMovies);
        }else{
            return databaseManager.getMovieList().map(this::returnMovieList);
        }
    }

    @Override
    public Single<Movie> getMovie(long movieId) {
        if (movieList!=null){
            return Single.create(emitter -> {
                Movie movie=filterMovieList(movieId, movieList);
                if (movie!=null){
                    emitter.onSuccess(movie);
                }else{
                    //TODO
                    emitter.onError(new Exception());
                }
            });
        }else{
            return getMovieList(1).map(movieList1 -> filterMovieList(movieId,movieList1));
        }
    }

    @Override
    public Single<Boolean> addFavourite(Movie movie) {
        return Single.create(emitter -> {
            databaseManager.updateMovie(movie);
            emitter.onSuccess(true);
        });
    }

    @Override
    public Single<List<Movie>> getFavouriteMovies() {
        return databaseManager.getMovieList().map(movies -> {
            List<Movie> favourites=new ArrayList<>();
            for (Movie movie : movies) {
                if (movie.isFavourite())favourites.add(movie);
            }
            return favourites;
        });
    }

    @Override
    public Single<Boolean> removeFavourite(Movie movie) {
        return Single.create(emitter -> {
            databaseManager.updateMovie(movie);
            emitter.onSuccess(true);
        });
    }

    private MovieList saveMovies(MovieList movieList) {
        List<Movie> movies=new ArrayList<>();
        movies.addAll(movieList.getMovieList());
        databaseManager.saveMovieList(movies);
        if (this.movieList==null) {
            this.movieList = movieList;
        }else{
            this.movieList.getMovieList().addAll(movieList.getMovieList());
            this.movieList.setPage(movieList.getPage());
        }
        return this.movieList;
    }

    private Movie filterMovieList(long movieId, MovieList movieList){
        for (Movie movie : movieList.getMovieList()) {
            if (movie.getId()==movieId){
                return movie;
            }
        }
        return null;
    }
    private MovieList returnMovieList(List<Movie> movies) {
        MovieList movieList=new MovieList();
        movieList.setMovieList(movies);
        return movieList;
    }

}
