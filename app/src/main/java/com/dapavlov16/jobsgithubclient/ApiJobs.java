package com.dapavlov16.jobsgithubclient;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiJobs {

    @GET("positions.json")
    Single<List<Vacancy>> vacancies();
}
