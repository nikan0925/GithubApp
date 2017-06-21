package com.nk.githubapp.di.module;

import com.nk.githubapp.GithubApp;
import com.nk.githubapp.di.scope.PerApplication;
import com.nk.githubapp.http.ApiClient;
import com.nk.githubapp.respository.Repository;
import com.nk.githubapp.respository.impl.RepositoryImpl;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/6/21.
 */
@Module
public class NetModule {
    private final GithubApp mGithubApp;

    public NetModule(GithubApp application) {
        this.mGithubApp = application;
    }

    @Provides
    @PerApplication
    Repository provideRepository(Retrofit retrofit) {
        return new RepositoryImpl(retrofit);
    }

    @Provides
    @PerApplication
    public Retrofit provideRetrofit() {

        return ApiClient.githubApiRetrofit;
    }
}
