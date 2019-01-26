package com.prvulovic.vukasin.data.di;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.prvulovic.vukasin.data.network.cast.service.CastNetworkService;
import com.prvulovic.vukasin.data.network.genre.service.GenreNetworkService;
import com.prvulovic.vukasin.data.network.movie.service.MovieNetworkService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.prvulovic.vukasin.data.utils.Constants.BASE_URL;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    MovieNetworkService provideMovieNetworkService(Retrofit retrofit){
        return retrofit.create(MovieNetworkService.class);
    }

    @Provides
    @Singleton
    GenreNetworkService provideGenreNetworkService(Retrofit retrofit){
        return retrofit.create(GenreNetworkService.class);
    }

    @Provides
    @Singleton
    CastNetworkService provideCastNetworkService(Retrofit retrofit){
        return retrofit.create(CastNetworkService.class);
    }
}
