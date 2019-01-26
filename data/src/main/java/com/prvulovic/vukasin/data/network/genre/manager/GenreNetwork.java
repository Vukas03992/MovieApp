package com.prvulovic.vukasin.data.network.genre.manager;

import com.prvulovic.vukasin.data.network.genre.mapper.GenreMapper;
import com.prvulovic.vukasin.data.network.genre.service.GenreNetworkService;
import com.prvulovic.vukasin.domain.entity.genre.Genre;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GenreNetwork {

    @Inject
    GenreNetworkService networkService;

    @Inject
    public GenreNetwork() {
    }

    public Single<List<Genre>> getGenre(String apiKey){
        return networkService.getGenres(apiKey).map(GenreMapper::genreListResponseToGenreList);
    }
}
