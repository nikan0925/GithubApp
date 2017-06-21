package com.nk.githubapp.http;

import com.nk.githubapp.http.util.EntityUtils;
import com.nk.githubapp.http.util.HttpUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class ApiClient {

    private ApiClient() {}

    public static final Retrofit githubApiRetrofit = new Retrofit.Builder()
        .baseUrl(ApiDefine.GITHUB_BASE)
        .client(HttpUtils.client)
        .addConverterFactory(GsonConverterFactory.create(EntityUtils.gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
}
