package com.prvulovic.vukasin.data.persistence.manager;

import com.prvulovic.vukasin.data.persistence.repository.AuthenticationRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PersistenceManager implements AuthenticationRepository {

    //todo
    private static final String API_KEY="c22d755514350d9836b3f9b173b3d763";

    @Inject public PersistenceManager() { }

    @Override
    public String getApiKey() {
        return API_KEY;
    }
}
