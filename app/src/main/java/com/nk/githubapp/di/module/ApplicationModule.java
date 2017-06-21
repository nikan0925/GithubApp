package com.nk.githubapp.di.module;

import android.app.Application;
import com.nk.githubapp.GithubApp;
import com.nk.githubapp.di.scope.PerApplication;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/6/21.
 */
@Module
public class ApplicationModule {
    private final GithubApp mGithubApp;


    public ApplicationModule(GithubApp application) {
        this.mGithubApp = application;
    }


    @Provides
    @PerApplication
    public GithubApp provideGithubApp() {
        return mGithubApp;
    }


    @Provides
    @PerApplication
    public Application provideApplication() {
        return mGithubApp;
    }

}
