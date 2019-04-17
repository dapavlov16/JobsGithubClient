package com.dapavlov16.jobsgithubclient.core;

import android.app.Application;

import com.dapavlov16.jobsgithubclient.network.ApiJobs;
import com.dapavlov16.jobsgithubclient.repository.VacanciesRepository;
import com.jakewharton.threetenabp.AndroidThreeTen;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;
import ru.terrakok.cicerone.Router;

public class JobsGithubApp extends Application {

    private static JobsGithubApp app;
    private Cicerone<Router> cicerone;
    private ApiJobs apiJobs;
    private static VacanciesRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        AndroidThreeTen.init(this);
        initCicerone();

        apiJobs = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiJobs.class);
        initRepository();
    }

    private void initCicerone() {
        cicerone = Cicerone.create();
    }

    private void initRepository() {
        repository = new VacanciesRepository(apiJobs);
    }

    public static VacanciesRepository getRepository() {
        return repository;
    }

    public NavigatorHolder getNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }

    public Router getRouter() {
        return cicerone.getRouter();
    }

    public static JobsGithubApp getApp() {
        return app;
    }

    public ApiJobs getApiJobs() {
        return apiJobs;
    }


}


