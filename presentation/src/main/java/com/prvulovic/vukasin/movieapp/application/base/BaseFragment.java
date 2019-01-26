package com.prvulovic.vukasin.movieapp.application.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prvulovic.vukasin.movieapp.application.injector.Injection;
import com.prvulovic.vukasin.movieapp.application.lifecycle.FragmentLifecycleTask;

import java.util.Set;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseFragment extends Fragment {

    @Inject Set<FragmentLifecycleTask> fragmentLifecycleTasks;

    private final CompositeDisposable compositeDisposable=new CompositeDisposable();
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        Injection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (FragmentLifecycleTask fragmentLifecycleTask : fragmentLifecycleTasks) {
            fragmentLifecycleTask.onCreate();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(layoutRes(),container,false);
        unbinder=ButterKnife.bind(this,view);
        onViewBound(view);
        compositeDisposable.addAll(subscriptions());
        for (FragmentLifecycleTask fragmentLifecycleTask : fragmentLifecycleTasks) {
            fragmentLifecycleTask.onEnterScope();
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        compositeDisposable.clear();
        if (unbinder!=null){
            unbinder.unbind();
            unbinder=null;
        }
        for (FragmentLifecycleTask fragmentLifecycleTask : fragmentLifecycleTasks) {
            fragmentLifecycleTask.onExitScope();
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (FragmentLifecycleTask fragmentLifecycleTask : fragmentLifecycleTasks) {
            fragmentLifecycleTask.onDestroy();
        }
        if (!getActivity().isChangingConfigurations()){
            Injection.clear(this);
        }
    }

    protected void onViewBound(View view) {

    }

    protected Disposable[] subscriptions() {
        return new Disposable[0];
    }

    @LayoutRes
    protected abstract int layoutRes();
}
