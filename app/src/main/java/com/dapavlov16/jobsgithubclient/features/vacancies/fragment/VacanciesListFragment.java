package com.dapavlov16.jobsgithubclient.features.vacancies.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dapavlov16.jobsgithubclient.R;
import com.dapavlov16.jobsgithubclient.core.BaseFragment;
import com.dapavlov16.jobsgithubclient.features.details.activity.VacancyDetailsActivity;
import com.dapavlov16.jobsgithubclient.features.vacancies.RecyclerViewAdapter;
import com.dapavlov16.jobsgithubclient.features.vacancies.activity.MainActivity;
import com.dapavlov16.jobsgithubclient.features.vacancies.viewmodel.VacanciesListViewModel;
import com.dapavlov16.jobsgithubclient.model.Vacancy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import static com.dapavlov16.jobsgithubclient.network.NetworkUtils.setData;

@SuppressWarnings("FieldCanBeLocal")
public class VacanciesListFragment extends BaseFragment {

    private int page = 1;

    private VacanciesListViewModel viewModel;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerViewAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(VacanciesListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_vacancies_list, container, false);


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
                Intent intent = new Intent(rootView.getContext(), VacancyDetailsActivity.class);
                Bundle args = new Bundle();
                args.putSerializable(MainActivity.KEY_VACANCY, item);
                intent.putExtras(args);
                rootView.getContext().startActivity(intent);
            }
        });
        adapter.setOnLoadMoreListener(new RecyclerViewAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                viewModel.updateData(page++);
                viewModel.setData(adapter);
            }
        });
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.removeAllViews();
                swipeRefreshLayout.setRefreshing(true);
                page = 1;
                setData(page, adapter, true);
                swipeRefreshLayout.setRefreshing(false);
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
        setData(page++, adapter, false);
    }
}
