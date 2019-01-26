package com.prvulovic.vukasin.data.di;

import dagger.Module;

@Module(includes = {
        RepositoryModule.class,
        NetworkModule.class,
        PersistenceModule.class,
        DatabaseModule.class
})
public abstract class DataModule {
}
