package com.prvulovic.vukasin.movieapp.application.injector;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class Injection {

    public static void inject(Activity activity){
        ActivityInjector.get(activity).inject(activity);
    }

    public static void clear(Activity activity){
        ActivityInjector.get(activity).clear(activity);
    }

    public static void inject(Fragment fragment){
        FragmentInjector.get(fragment.getActivity()).inject(fragment);
    }

    public static void clear(Fragment fragment){
        FragmentInjector.get(fragment.getActivity()).clear(fragment);
    }
}
