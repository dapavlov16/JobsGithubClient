package com.dapavlov16.jobsgithubclient.network;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiJobs {

    @GET("positions.json")
    Single<List<Vacancy>> vacancies(@Query("page") int page);
}
