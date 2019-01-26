package com.prvulovic.vukasin.movieapp.application.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prvulovic.vukasin.movieapp.R;
import com.prvulovic.vukasin.movieapp.application.injector.FragmentInjector;
import com.prvulovic.vukasin.movieapp.application.injector.Injection;
import com.prvulovic.vukasin.movieapp.application.lifecycle.ActivityLifecycleTask;

import java.util.Set;
import java.util.UUID;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements ScreenProvider{

    private static final String INSTANCE_ID="instanceID";

    @Inject ScreenNavigator screenNavigator;
    @Inject
    FragmentInjector fragmentInjector;
    @Inject Set<ActivityLifecycleTask> activityLifecycleTaskSet;

    private String instanceId;
    private Unbinder unbinder;
    private CompositeDisposable disposables=new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState!=null){
            instanceId=savedInstanceState.getString(INSTANCE_ID);
        }else{
            instanceId=UUID.randomUUID().toString();
        }
        Injection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        unbinder=ButterKnife.bind(this);
        if(findViewById(R.id.fragment_container)==null)throw new NullPointerException("Activity must have a view with id: fragment_container");
        disposables.addAll(subscriptions());
        for (ActivityLifecycleTask activityLifecycleTask : activityLifecycleTaskSet) {
            activityLifecycleTask.onCreate(this);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        setupFullWindowDisplayMode();
        for (ActivityLifecycleTask activityLifecycleTask : activityLifecycleTaskSet) {
            activityLifecycleTask.onStart(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fullscreen();
        for (ActivityLifecycleTask activityLifecycleTask : activityLifecycleTaskSet) {
            activityLifecycleTask.onResume(this);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        for (ActivityLifecycleTask activityLifecycleTask : activityLifecycleTaskSet) {
            activityLifecycleTask.onPause(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(null);
        for (ActivityLifecycleTask activityLifecycleTask : activityLifecycleTaskSet) {
            activityLifecycleTask.onStop(this);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(INSTANCE_ID,instanceId);
    }

    @Override
    public void onBackPressed() {
        if (!screenNavigator.pop()){
            super.onBackPressed();
        }
    }

    private void setupFullWindowDisplayMode() {
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> fullscreen());
    }

    public void fullscreen(){
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    @LayoutRes protected abstract int layoutRes();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder!=null){
            unbinder.unbind();
            unbinder=null;
        }
        disposables.clear();
        if (isFinishing()){
            Injection.clear(this);
        }
        for (ActivityLifecycleTask activityLifecycleTask : activityLifecycleTaskSet) {
            activityLifecycleTask.onDestroy(this);
        }
    }

    protected Disposable[] subscriptions(){
        return new Disposable[0];
    }

    public String getInstanceId() {
        return instanceId;
    }

    public FragmentInjector getFragmentInjector() {
        return fragmentInjector;
    }
}
