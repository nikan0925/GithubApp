package com.nk.githubapp.respository;

import com.nk.githubapp.http.entity.GithubUserBean;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/21.
 */

public interface Repository {

    Observable<GithubUserBean> getUserRepos(String name);
}
