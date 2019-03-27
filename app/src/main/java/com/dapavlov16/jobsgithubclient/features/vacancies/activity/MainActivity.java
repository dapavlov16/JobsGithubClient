package com.dapavlov16.jobsgithubclient.features.vacancies.activity;

import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.dapavlov16.jobsgithubclient.R;
import com.dapavlov16.jobsgithubclient.core.BaseActivity;
import com.dapavlov16.jobsgithubclient.features.Screens;


public class MainActivity extends BaseActivity {

    public static final String KEY_VACANCY = "VACANCY";

    private BottomNavigationBar bottomNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        router.replaceScreen(new Screens.VacanciesListScreen());
        bottomNavigationBar = findViewById(R.id.bnb_main_navigation);
        initViews();
    }

    private void initViews() {
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_red_700_48dp, R.string.home))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_border_red_a700_48dp, R.string.favorite))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                switch (position){
                    case 0:
                        router.navigateTo(new Screens.VacanciesListScreen());
                        break;
                    case 1:
                        router.navigateTo(new Screens.FavoritesListScreen());
                        break;
                }
            }
        });
    }
}
