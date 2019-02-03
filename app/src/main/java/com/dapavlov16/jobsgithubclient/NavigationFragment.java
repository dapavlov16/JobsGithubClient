package com.dapavlov16.jobsgithubclient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.Map;

import static com.dapavlov16.jobsgithubclient.network.NetworkUtils.setData;
import static com.dapavlov16.jobsgithubclient.network.NetworkUtils.setDataById;

public class NavigationFragment extends Fragment {

    public static final String ARG_TITLE = "arg_title";
    //public static final String KEY_VACANCY = "VACANCY";

    private int page = 1;

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerViewAdapter adapter;
    private SharedPreferences preferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_bottom_navigation, container, false);

        preferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());
        swipeRefreshLayout = rootView.findViewById(R.id.swipeRefresh);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
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
        recyclerView.setAdapter(adapter);
        switch (getArguments().getInt(ARG_TITLE)){
            case 1:
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
                setData(page++, adapter, false);
                adapter.setOnLoadMoreListener(new RecyclerViewAdapter.OnLoadMoreListener() {
                    @Override
                    public void onLoadMore() {
                        setData(page++, adapter, false);
                    }
                });
            case 2:
                Map<String, ?> favoriteMap = preferences.getAll();
                setDataById(favoriteMap, adapter);
        }
        return rootView;
    }
}
