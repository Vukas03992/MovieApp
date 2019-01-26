package com.prvulovic.vukasin.movieapp.movie.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.prvulovic.vukasin.movieapp.R;
import com.prvulovic.vukasin.movieapp.application.base.BaseFragment;
import com.prvulovic.vukasin.movieapp.movie.adapter.MovieListAdapter;
import com.prvulovic.vukasin.movieapp.movie.viewmodel.MovieListViewModel;

import java.util.UUID;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class MovieListFragment extends BaseFragment {

    @Inject MovieListAdapter movieListAdapter;
    @Inject
    MovieListViewModel viewModel;

    public static MovieListFragment newInstance(){
        MovieListFragment movieListFragment=new MovieListFragment();
        Bundle bundle=new Bundle();
        bundle.putString("instance_id",UUID.randomUUID().toString());
        movieListFragment.setArguments(bundle);
        return movieListFragment;
    }

    @BindView(R.id.fragment_movie_list_recycler_view)RecyclerView movieListView;
    @BindView(R.id.fragment_movie_list_progress_bar)FrameLayout loader;

    @Override
    protected void onViewBound(View view) {
        GridLayoutManager manager=new GridLayoutManager(getContext(),2);
        movieListView.setLayoutManager(manager);
        movieListView.setAdapter(movieListAdapter);

        movieListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!movieListView.canScrollVertically(1)){
                    viewModel.loadMore();
                }
            }
        });
    }

    @OnClick(R.id.fragment_movie_list_refresh) void onRefresh(){
        viewModel.refresh();
    }

    @OnClick(R.id.fragment_movie_list_favourite) void onFavourite(){
        viewModel.favourite();
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[]{
                viewModel.getLoading()
                .subscribe(flag->{
                    if (flag)loader.setVisibility(View.VISIBLE);
                    else loader.setVisibility(View.GONE);
                })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.fragment_movie_list;
    }
}
