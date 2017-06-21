package com.nk.githubapp.http;

import com.nk.githubapp.http.entity.GithubUserBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/21.
 */

public interface GithubApiService {

    /**
     * 获取某个用户的 Repos
     *
     * @param name
     * @return
     */
    @GET("users/{name}")
    Observable<GithubUserBean> getUserRepos(@Path("name") String name);

}
