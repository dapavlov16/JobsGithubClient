package com.dapavlov16.jobsgithubclient.features.vacancies.viewmodel;

import android.app.Application;

import com.dapavlov16.jobsgithubclient.core.BaseViewModel;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class VacanciesListViewModel extends BaseViewModel {

    private BehaviorSubject<Object> vacanciesSubject = BehaviorSubject.create();

    public Observable<Object> getVacanciesObservable() {
        return vacanciesSubject.hide();
    }

    public VacanciesListViewModel(@NonNull Application application) {
        super(application);
    }
}
