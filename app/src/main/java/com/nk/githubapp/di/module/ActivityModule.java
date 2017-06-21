package com.nk.githubapp.di.module;

import android.app.Activity;
import android.content.Context;
import com.nk.githubapp.di.scope.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private final Activity mActivity;


    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }


    @Provides
    @PerActivity
    public Context context() {
        return mActivity;
    }
}
