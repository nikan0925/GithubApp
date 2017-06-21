package com.nk.githubapp.respository.impl;

import com.nk.githubapp.http.GithubApiService;
import com.nk.githubapp.http.entity.GithubUserBean;
import com.nk.githubapp.respository.Repository;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/21.
 */

public class RepositoryImpl implements Repository {

    private GithubApiService mGithubApiService;

    public RepositoryImpl(Retrofit retrofit) {
        mGithubApiService = retrofit.create(GithubApiService.class);
    }

    @Override public Observable<GithubUserBean> getUserRepos(String name) {
        return mGithubApiService.getUserRepos(name);
    }
}
