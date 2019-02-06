package com.dapavlov16.jobsgithubclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import android.app.Application;

import com.dapavlov16.jobsgithubclient.network.ApiJobs;
import com.jakewharton.threetenabp.AndroidThreeTen;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobsGithubApp extends Application {

    private static JobsGithubApp app;
    private ApiJobs apiJobs;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        AndroidThreeTen.init(this);

        apiJobs = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiJobs.class);
    }


    public static JobsGithubApp getApp() {
        return app;
    }

    public ApiJobs getApiJobs() {
        return apiJobs;
    }


}
