package com.prvulovic.vukasin.movieapp.movie.activity;

import android.support.v4.app.Fragment;

import com.prvulovic.vukasin.movieapp.R;
import com.prvulovic.vukasin.movieapp.application.base.BaseActivity;
import com.prvulovic.vukasin.movieapp.movie.fragment.MovieListFragment;

public class MovieActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_movie;
    }

    @Override
    public Fragment initialFragment() {
        return MovieListFragment.newInstance();
    }
}
