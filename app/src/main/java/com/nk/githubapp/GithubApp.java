package com.nk.githubapp;

import android.app.Application;
import com.nk.githubapp.di.component.ApplicationComponent;
import com.nk.githubapp.di.component.DaggerApplicationComponent;
import com.nk.githubapp.di.module.ApplicationModule;
import com.nk.githubapp.di.module.NetModule;

/**
 * Created by Administrator on 2017/6/21.
 */

public class GithubApp extends Application {

    private ApplicationComponent mApplicationComponent;


    @Override public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder().
            applicationModule(new ApplicationModule(this))
            .netModule(new NetModule(this)).build();
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
