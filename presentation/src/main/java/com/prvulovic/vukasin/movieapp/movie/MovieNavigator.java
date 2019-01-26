package com.prvulovic.vukasin.movieapp.movie;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.prvulovic.vukasin.movieapp.R;
import com.prvulovic.vukasin.movieapp.application.base.BaseFragment;
import com.prvulovic.vukasin.movieapp.application.base.ScreenNavigator;
import com.prvulovic.vukasin.movieapp.application.base.ScreenProvider;
import com.prvulovic.vukasin.movieapp.application.di.scope.ActivityScope;
import com.prvulovic.vukasin.movieapp.application.lifecycle.ActivityLifecycleTask;
import com.prvulovic.vukasin.movieapp.movie.fragment.MovieFragment;

import javax.inject.Inject;

import static com.prvulovic.vukasin.movieapp.utils.Constants.SCREEN_MOVIE;

@ActivityScope
public class MovieNavigator extends ActivityLifecycleTask implements ScreenNavigator {

    private FragmentManager fragmentManager;

    @Inject
    public MovieNavigator() { }

    @Override
    public void onCreate(AppCompatActivity activity) {
        fragmentManager=activity.getSupportFragmentManager();
        if (fragmentManager.getFragments().size()==0) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ((ScreenProvider) activity).initialFragment())
                    .commit();
        }
    }

    public void showScreen(int screen, Bundle bundle){
        switch (screen){
            case SCREEN_MOVIE:
                if (bundle!=null)showScreen(MovieFragment.newInstance(bundle.getLong("movieId")),true);
                break;
        }
    }

    private void showScreen(BaseFragment baseFragment, boolean addToBackStack){
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container,baseFragment);
        if (addToBackStack)transaction.addToBackStack("");
        transaction.commit();
    }

    @Override
    public boolean pop() {
        return fragmentManager!=null && fragmentManager.popBackStackImmediate();
    }
}
