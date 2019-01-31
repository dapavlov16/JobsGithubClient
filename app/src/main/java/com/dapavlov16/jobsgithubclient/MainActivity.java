package com.dapavlov16.jobsgithubclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dapavlov16.jobsgithubclient.database.VacancyDatabase;
import com.dapavlov16.jobsgithubclient.model.Vacancy;
import com.dapavlov16.jobsgithubclient.network.ApiJobs;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private VacancyDatabase db;
    private int page = 1;

    public static final String KEY_VACANCY = "VACANCY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Vacancy item) {
                Intent intent = new Intent(MainActivity.this, VacancyActivity.class);
                Bundle args = new Bundle();
                args.putSerializable(KEY_VACANCY, item);
                intent.putExtras(args);
                MainActivity.this.startActivity(intent);
            }
        }, recyclerView);
        recyclerView.setAdapter(adapter);
        setData(page);
        page++;
        adapter.setOnLoadMoreListener(new RecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                setData(page);
            }
        });
    }

    public void setData(int page){
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
                        adapter.setItems(vacancyList);
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
