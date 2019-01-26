package com.prvulovic.vukasin.data.network.cast.service;

import com.prvulovic.vukasin.data.network.cast.model.CastResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CastNetworkService {

    @GET("/3/movie/{movie_id}/credits")
    Single<CastResponse> getCast( @Path("movie_id") long movieId, @Query("api_key") String apiKey);
}
