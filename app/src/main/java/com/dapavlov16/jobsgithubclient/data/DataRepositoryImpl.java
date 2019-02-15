package com.dapavlov16.jobsgithubclient.data;

import com.dapavlov16.jobsgithubclient.model.Vacancy;
import com.dapavlov16.jobsgithubclient.network.ApiJobs;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class DataRepositoryImpl implements DataRepository {

    private final ApiJobs api;

    public DataRepositoryImpl(ApiJobs api) {
        this.api = api;
    }


    @Override
    public Single<List<Vacancy>> getVacancies(int page) {
        return api.vacancies(page)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<Vacancy> getVacancy(String id) {
        return api
                .vacancy(id)
                .subscribeOn(Schedulers.io());
    }
}
