package com.dapavlov16.jobsgithubclient.features.vacancies.viewmodel;

import android.app.Application;

import com.dapavlov16.jobsgithubclient.core.BaseViewModel;
import com.dapavlov16.jobsgithubclient.core.JobsGithubApp;
import com.dapavlov16.jobsgithubclient.features.vacancies.RecyclerViewAdapter;
import com.dapavlov16.jobsgithubclient.model.Vacancy;
import com.dapavlov16.jobsgithubclient.repository.VacanciesRepository;

import java.util.List;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.BehaviorSubject;

public class VacanciesListViewModel extends BaseViewModel {

    private VacanciesRepository repository;
    private Observable<List<Vacancy>> repositoryObservable;
    private BehaviorSubject<Object> vacanciesSubject = BehaviorSubject.create();

    public Observable<Object> getVacanciesObservable() {
        return vacanciesSubject.hide();
    }

    public VacanciesListViewModel(@NonNull Application application) {
        super(application);
        repository = JobsGithubApp.getRepository();
        repositoryObservable = repository.getRepositoryObservable();
    }

    public void updateData(int page) {
        repository.updateData(page);
    }

    public void setData(final RecyclerViewAdapter adapter) {
        repositoryObservable.subscribe(new DisposableObserver<List<Vacancy>>() {
            @Override
            public void onNext(List<Vacancy> vacancies) {
                adapter.setItems(vacancies, false);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
            }
        });
    }
}
