package com.dapavlov16.jobsgithubclient.network;

import com.dapavlov16.jobsgithubclient.JobsGithubApp;
import com.dapavlov16.jobsgithubclient.RecyclerViewAdapter;
import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class NetworkUtils {

    public static void setData(int page, final RecyclerViewAdapter adapter, final boolean isRefresh) {
        JobsGithubApp
                .getApp()
                .getApiJobs()
                .vacancies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Vacancy>>() {
                    @Override
                    public void onSuccess(final List<Vacancy> vacancyList) {
                        adapter.setItems(vacancyList, isRefresh);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void setDataById(Map<String, ?> preference, final RecyclerViewAdapter adapter) {
        for (Map.Entry<String, ?> id : preference.entrySet()) {
            if (id.getValue() instanceof String) {
                JobsGithubApp
                        .getApp()
                        .getApiJobs().vacancy(id.getValue())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableSingleObserver<Vacancy>() {
                            @Override
                            public void onSuccess(Vacancy vacancy) {
                                //vacancyList.add(vacancy);
                                adapter.setItems(new ArrayList<>(Collections.singletonList(vacancy)), false);
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
