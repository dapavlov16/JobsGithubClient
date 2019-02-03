package com.dapavlov16.jobsgithubclient;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    /*private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;*/
    //private VacancyDatabase db;
    //private int page = 1;//
    private List<NavigationFragment> fragments = new ArrayList<>();

    public static final String KEY_VACANCY = "VACANCY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildFragmentList();
        switchFragment(0);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        switchFragment(0);
                        return true;
                    case R.id.navigation_favorite:
                        switchFragment(1);
                        return true;
                }
                return false;
            }
        });
        /*swipeRefreshLayout = findViewById(R.id.swipeRefresh);//
        recyclerView = findViewById(R.id.recyclerView);//
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
        });*/
    }

    private void buildFragmentList() {
        NavigationFragment homeFragment = buildFragment(1);
        NavigationFragment favoriteFragment = buildFragment(2);

        fragments.add(homeFragment);
        fragments.add(favoriteFragment);
    }

    private NavigationFragment buildFragment(int fragmentType) {
        NavigationFragment fragment = new NavigationFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(NavigationFragment.ARG_TITLE, fragmentType);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void switchFragment(int pos) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragments.get(pos))
                .commit();
    }
}
