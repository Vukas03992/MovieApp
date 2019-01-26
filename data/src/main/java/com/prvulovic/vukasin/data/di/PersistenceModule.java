package com.prvulovic.vukasin.data.di;

import com.prvulovic.vukasin.data.persistence.manager.PersistenceManager;
import com.prvulovic.vukasin.data.persistence.repository.AuthenticationRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {

    @Provides
    @Singleton
    AuthenticationRepository provideAuthenticationRepository(PersistenceManager manager){
        return manager;
    }
}
