package com.prvulovic.vukasin.movieapp.movie.viewmodel;

import com.prvulovic.vukasin.domain.entity.movie.Movie;
import com.prvulovic.vukasin.domain.usecase.movie.AddFavourite;
import com.prvulovic.vukasin.domain.usecase.movie.GetMovie;
import com.prvulovic.vukasin.movieapp.application.di.scope.FragmentScope;
import com.prvulovic.vukasin.movieapp.application.executor.ThreadExecutor;
import com.prvulovic.vukasin.movieapp.application.lifecycle.DisposableManager;
import com.prvulovic.vukasin.movieapp.application.lifecycle.FragmentLifecycleTask;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

@FragmentScope
public class MovieViewModel extends FragmentLifecycleTask {

    private PublishSubject<Movie> moviePublisher=PublishSubject.create();

    @Inject DisposableManager disposableManager;
    @Inject ThreadExecutor executor;

    @Inject GetMovie getMovie;
    @Inject AddFavourite addFavourite;

    private Movie movie;

    @Inject
    public MovieViewModel() {
    }

    public void setMovieId(long movieId) {
        disposableManager.add(movie(movieId));
    }

    private Disposable movie(long movieId){
        return getMovie.get(movieId)
                .subscribeOn(Schedulers.from(executor))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movie->{
                    this.movie=movie;
                    moviePublisher.onNext(movie);
                },error->{
                    //TODO
                });
    }

    public Observable<Movie> getMoviePublisher() {
        return moviePublisher;
    }

    public void favouriteClicked() {
        disposableManager.add(addFavourite.add(movie)
                .subscribeOn(Schedulers.from(executor))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success->{

                },error->{
                    //TODO
                }));
    }
}
