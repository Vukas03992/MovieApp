package com.prvulovic.vukasin.data.network.movie.manager;

import com.prvulovic.vukasin.data.network.movie.mapper.MovieMapper;
import com.prvulovic.vukasin.data.network.movie.service.MovieNetworkService;
import com.prvulovic.vukasin.domain.entity.movie.MovieList;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class MovieNetwork {

    @Inject MovieNetworkService networkService;

    @Inject
    public MovieNetwork() { }

    public Single<MovieList> get(String apiKey, int page){
        return networkService.getMovies(apiKey, page).map(MovieMapper::movieListResponseToMovieList);
    }
}
