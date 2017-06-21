package com.nk.githubapp.di.module;

import com.nk.githubapp.mvp.contract.GithubRepoContract;
import com.nk.githubapp.mvp.presenter.GithubRepoPresenter;
import com.nk.githubapp.respository.Repository;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/6/21.
 */
@Module
public class GithubRepoModule {

    @Provides
    public GithubRepoContract.Presenter getGithubRepoPresenter(Repository repository) {
        return new GithubRepoPresenter(repository);
    }

}
