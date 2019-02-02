package com.dapavlov16.jobsgithubclient.network;

import android.support.v7.widget.RecyclerView;

import com.dapavlov16.jobsgithubclient.RecyclerViewAdapter;
import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static void setData(int page, final RecyclerViewAdapter adapter, final boolean isRefresh) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiJobs apiJobs = retrofit.create(ApiJobs.class);
        apiJobs.vacancies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Vacancy>>() {
                    @Override
                    public void onSuccess(final List<Vacancy> vacancyList) {
                        adapter.setItems(vacancyList, isRefresh);
                        adapter.notifyDataSetChanged();
                        /* Пытаюсь записать лист в базу
                        Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                db.getVacancyDao().insertAll(vacancyList);
                            }
                        }).subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                            }

                            @Override
                            public void onError(Throwable e) {
                            }
                        });*/
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void setDataById(Map<String, ?> preference, final RecyclerViewAdapter adapter){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiJobs apiJobs = retrofit.create(ApiJobs.class);
        for (Map.Entry<String, ?> id : preference.entrySet()) {
            if (id.getValue() instanceof String) {
                apiJobs.vacancy(id.getValue())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableSingleObserver<Vacancy>() {
                            @Override
                            public void onSuccess(Vacancy vacancy) {
                                //vacancyList.add(vacancy);
                                adapter.setItems(new ArrayList<>(Arrays.asList(vacancy)), false);
                                adapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }
                        });
            }
        }
    }
}
