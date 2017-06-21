package com.nk.githubapp.http.util;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public final class HttpUtils {

    private HttpUtils() {
    }


    public static final OkHttpClient client = new OkHttpClient.Builder()
        .addInterceptor(createHttpLoggingInterceptor())
        .build();


    private static Interceptor createHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }


}
