package com.prvulovic.vukasin.data.network.movie.service;

import com.prvulovic.vukasin.data.network.movie.model.MovieListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieNetworkService {

    @GET("/3/movie/top_rated")
    Single<MovieListResponse> getMovies(@Query("api_key") String apiKey, @Query("page") int page);
}
