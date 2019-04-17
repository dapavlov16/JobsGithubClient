package com.dapavlov16.jobsgithubclient.repository;

import com.dapavlov16.jobsgithubclient.model.Vacancy;
import com.dapavlov16.jobsgithubclient.network.ApiJobs;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class VacanciesRepository {

    private BehaviorSubject<List<Vacancy>> repositorySubject = BehaviorSubject.create();
    private ApiJobs api;

    public VacanciesRepository(ApiJobs api) {
        this.api = api;
        api.vacancies(0)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Vacancy>>() {
                    @Override
                    public void onSuccess(final List<Vacancy> vacancyList) {
                        repositorySubject.onNext(vacancyList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public Observable<List<Vacancy>> getRepositoryObservable() {
        return repositorySubject.hide();
    }

    public void updateData(int page) {
        api.vacancies(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Vacancy>>() {
                    @Override
                    public void onSuccess(final List<Vacancy> vacancyList) {
                        repositorySubject.onNext(vacancyList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}
