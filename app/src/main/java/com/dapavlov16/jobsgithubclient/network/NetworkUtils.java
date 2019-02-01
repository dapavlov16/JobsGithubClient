package com.dapavlov16.jobsgithubclient.network;

import com.dapavlov16.jobsgithubclient.RecyclerViewAdapter;
import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    public static void setData(int page, final RecyclerViewAdapter adapter, final boolean isRefresh){
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
}
