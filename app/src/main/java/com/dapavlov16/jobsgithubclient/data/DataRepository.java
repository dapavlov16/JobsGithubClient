package com.dapavlov16.jobsgithubclient.data;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.List;

import io.reactivex.Single;

public interface DataRepository {

    Single<List<Vacancy>> getVacancies(int page);

    Single<Vacancy> getVacancy(String id);
}
