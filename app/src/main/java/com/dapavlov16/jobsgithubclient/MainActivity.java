package com.dapavlov16.jobsgithubclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

import static com.dapavlov16.jobsgithubclient.network.NetworkUtils.setData;


public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    //private VacancyDatabase db;
    private int page = 1;

    public static final String KEY_VACANCY = "VACANCY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
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
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
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
    }
}
