package com.prvulovic.vukasin.data.network.genre.service;

import com.prvulovic.vukasin.data.network.genre.model.GenreListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GenreNetworkService {

    @GET("/3/genre/movie/list")
    Single<GenreListResponse> getGenres(@Query("api_key") String apiKey);
}
