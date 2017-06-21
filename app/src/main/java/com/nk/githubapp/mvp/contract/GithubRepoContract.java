package com.nk.githubapp.mvp.contract;

import com.nk.githubapp.http.entity.GithubUserBean;

/**
 * Created by Administrator on 2017/6/21.
 */

public interface GithubRepoContract {

    interface View {
        void showError(String errorstring);

        void showRepo(GithubUserBean reposListBean);
    }


    interface Presenter {
        void attachView(GithubRepoContract.View view);

        void onStop();

        void refresh(String name);
    }

}
