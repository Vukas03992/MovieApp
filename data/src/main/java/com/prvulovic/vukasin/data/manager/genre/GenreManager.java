package com.prvulovic.vukasin.data.manager.genre;

import android.annotation.SuppressLint;

import com.prvulovic.vukasin.data.network.genre.manager.GenreNetwork;
import com.prvulovic.vukasin.data.network.state.NetworkStateManager;
import com.prvulovic.vukasin.data.persistence.repository.AuthenticationRepository;
import com.prvulovic.vukasin.domain.entity.genre.Genre;
import com.prvulovic.vukasin.domain.repository.genre.GenreRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class GenreManager implements GenreRepository {

    @Inject
    GenreNetwork genreNetwork;
    @Inject
    AuthenticationRepository authenticationRepository;
    @Inject
    NetworkStateManager networkStateManager;

    @Inject
    public GenreManager() {
    }

    @Override
    public Single<List<Genre>> getGenre() {
        return genreNetwork.getGenre(authenticationRepository.getApiKey());
    }

    @Override
    public Single<Map<Long, String>> getGenreIdNameMap() {
        if (networkStateManager.getConnection()) {
            return getGenre().map(this::convertGenreListToGenreMap);
        }else{
            return Single.create(emitter->{
                emitter.onSuccess(new HashMap());
            });
        }
    }

    //TODO
    private Map<Long,String> convertGenreListToGenreMap(List<Genre> genreList){
        HashMap<Long,String> idToName=new HashMap<>();
        for (Genre genre : genreList) {
            idToName.put(genre.getId(),genre.getName());
        }
        return idToName;
    }
}
