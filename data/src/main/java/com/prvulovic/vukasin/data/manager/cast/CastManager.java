package com.prvulovic.vukasin.data.manager.cast;

import com.prvulovic.vukasin.data.network.cast.manager.CastNetwork;
import com.prvulovic.vukasin.data.network.state.NetworkStateManager;
import com.prvulovic.vukasin.data.persistence.repository.AuthenticationRepository;
import com.prvulovic.vukasin.domain.entity.cast.CastMember;
import com.prvulovic.vukasin.domain.repository.cast.CastRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class CastManager implements CastRepository {

    @Inject
    CastNetwork castNetwork;
    @Inject
    AuthenticationRepository authenticationRepository;
    @Inject
    NetworkStateManager networkStateManager;

    @Inject
    public CastManager() {
    }

    @Override
    public Single<List<CastMember>> getCast(long movieId) {
        if (networkStateManager.getConnection()) {
            return castNetwork.getCast(movieId, authenticationRepository.getApiKey());
        }else{
            return Single.create(emitter->{
                emitter.onSuccess(new ArrayList<>());
            });
        }
    }
}
