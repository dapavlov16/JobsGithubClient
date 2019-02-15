package com.dapavlov16.jobsgithubclient;

import android.app.Application;

import com.dapavlov16.jobsgithubclient.data.DataRepository;
import com.dapavlov16.jobsgithubclient.data.DataRepositoryImpl;
import com.dapavlov16.jobsgithubclient.network.ApiJobs;
import com.jakewharton.threetenabp.AndroidThreeTen;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobsGithubApp extends Application {

    private static JobsGithubApp app;
    private DataRepository dataRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        AndroidThreeTen.init(this);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();


        dataRepository = new DataRepositoryImpl(new Retrofit.Builder()
                .client(client)
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiJobs.class));
    }


    public static JobsGithubApp getApp() {
        return app;
    }

    public DataRepository getDataRepository() {
        return dataRepository;
    }
}
