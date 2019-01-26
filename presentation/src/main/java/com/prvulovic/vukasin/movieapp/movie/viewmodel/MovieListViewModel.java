package com.prvulovic.vukasin.movieapp.movie.viewmodel;

import android.os.Bundle;
import android.util.Log;

import com.prvulovic.vukasin.domain.entity.movie.MovieList;
import com.prvulovic.vukasin.domain.usecase.movie.GetFavouriteMovies;
import com.prvulovic.vukasin.domain.usecase.movie.GetMovieList;
import com.prvulovic.vukasin.domain.usecase.movie.RemoveFavourite;
import com.prvulovic.vukasin.movieapp.application.di.scope.FragmentScope;
import com.prvulovic.vukasin.movieapp.application.executor.ThreadExecutor;
import com.prvulovic.vukasin.movieapp.application.lifecycle.DisposableManager;
import com.prvulovic.vukasin.movieapp.application.lifecycle.FragmentLifecycleTask;
import com.prvulovic.vukasin.movieapp.movie.MovieNavigator;
import com.prvulovic.vukasin.movieapp.movie.adapter.MovieListAdapter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

import static com.prvulovic.vukasin.movieapp.utils.Constants.SCREEN_MOVIE;

@FragmentScope
public class MovieListViewModel extends FragmentLifecycleTask {

    private PublishSubject<Boolean> loading=PublishSubject.create();

    @Inject DisposableManager disposableManager;
    @Inject ThreadExecutor executor;

    @Inject MovieListAdapter movieListAdapter;
    @Inject MovieNavigator navigator;

    //usecase
    @Inject GetMovieList getMovieList;
    @Inject
    GetFavouriteMovies getFavouriteMovies;
    @Inject
    RemoveFavourite removeFavourite;

    private boolean favouriteList;
    private MovieList movieList;

    @Inject public MovieListViewModel() { }

    @Override
    public void onCreate() {
        disposableManager.add(movieList(1));
    }

    public void onMovieClick(long movieId) {
        Bundle bundle=new Bundle();
        bundle.putLong("movieId",movieId);
        navigator.showScreen(SCREEN_MOVIE,bundle);
    }

    private Disposable movieList(int page){
        return getMovieList.get(page)
                .subscribeOn(Schedulers.from(executor))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__->loading.onNext(true))
                .doOnSuccess(__->loading.onNext(false))
                .doOnError(__->loading.onNext(false))
                .subscribe(movieList -> {
                    favouriteList=false;
                    this.movieList=movieList;
                    movieListAdapter.setData(movieList.getMovieList());
                },error->{
                    //TODO
                });
    }

    public Observable<Boolean> getLoading(){
        return loading;
    }

    public void refresh() {
        disposableManager.add(movieList(1));
    }


    public void favourite() {
        loading.onNext(true);
        disposableManager.add(getFavouriteMovies.get()
                .subscribeOn(Schedulers.from(executor))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__->loading.onNext(true))
                .doOnSuccess(__->loading.onNext(false))
                .doOnError(__->loading.onNext(false))
                .subscribe(movieList -> {
                    favouriteList=true;
                    movieListAdapter.setData(movieList);
                },error->{
                    //TODO
                }));
    }

    public boolean removeFavourite(long id) {
        if (favouriteList){
            disposableManager.add(removeFavourite.remove(id)
                    .subscribeOn(Schedulers.from(executor))
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(__->loading.onNext(true))
                    .doOnSuccess(__->loading.onNext(false))
                    .doOnError(__->loading.onNext(false))
                    .subscribe(movieList -> {
                        favouriteList=true;
                        movieListAdapter.setData(movieList);
                    },error->{
                        //TODO
                    }));
            return true;
        }else {
            return false;
        }
    }

    public void loadMore() {
        if (movieList.getPage()!=-1)
        disposableManager.add(movieList(movieList.getPage()+1));
    }
}
