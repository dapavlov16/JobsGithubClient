package com.dapavlov16.jobsgithubclient.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dapavlov16.jobsgithubclient.JobsGithubApp;
import com.dapavlov16.jobsgithubclient.MainActivity;
import com.dapavlov16.jobsgithubclient.R;
import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableSingleObserver;

@SuppressWarnings("FieldCanBeLocal")
public class VacanciesFragment extends Fragment {


    private int page = 1;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerViewAdapter adapter;


    public VacanciesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_vacancies, container, false);


        swipeRefreshLayout = rootView.findViewById(R.id.srl_update_vacancies);
        recyclerView = rootView.findViewById(R.id.rv_vacancies);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecyclerViewAdapter(recyclerView);
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Vacancy item) {
                Intent intent = new Intent(rootView.getContext(), VacancyActivity.class);
                Bundle args = new Bundle();
                args.putSerializable(MainActivity.KEY_VACANCY, item);
                intent.putExtras(args);
                rootView.getContext().startActivity(intent);
            }
        });
        adapter.setOnLoadMoreListener(new RecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                init();
            }
        });
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.setItems(new ArrayList<Vacancy>(),true);
                page = 0;
                init();
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        JobsGithubApp
                .getApp()
                .getDataRepository()
                .getVacancies(page)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                })
                .subscribe(new DisposableSingleObserver<List<Vacancy>>() {
                    @Override
                    protected void onStart() {
                        super.onStart();
                        swipeRefreshLayout.setRefreshing(true);
                    }

                    @Override
                    public void onSuccess(final List<Vacancy> vacancyList) {
                        adapter.setItems(vacancyList, false);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}
