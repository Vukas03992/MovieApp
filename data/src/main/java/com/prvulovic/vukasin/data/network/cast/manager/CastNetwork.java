package com.prvulovic.vukasin.data.network.cast.manager;

import com.prvulovic.vukasin.data.network.cast.mapper.CastMapper;
import com.prvulovic.vukasin.data.network.cast.service.CastNetworkService;
import com.prvulovic.vukasin.domain.entity.cast.CastMember;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class CastNetwork {

    @Inject
    CastNetworkService networkService;

    @Inject
    public CastNetwork() {
    }

    public Single<List<CastMember>> getCast(long movieId, String apiKey){
        return networkService.getCast(movieId,apiKey).map(CastMapper::castResponseToCastMemeberList);
    }
}
