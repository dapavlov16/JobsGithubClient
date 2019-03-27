package com.dapavlov16.jobsgithubclient.core;

import android.os.Bundle;

import com.dapavlov16.jobsgithubclient.R;

import androidx.appcompat.app.AppCompatActivity;
import ru.terrakok.cicerone.Router;
import ru.terrakok.cicerone.android.support.SupportAppNavigator;

public abstract class BaseActivity extends AppCompatActivity {

    protected Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        router = JobsGithubApp.getApp().getRouter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        JobsGithubApp.getApp().getNavigatorHolder().setNavigator(new SupportAppNavigator(this, R.id.container));
    }

    @Override
    protected void onPause() {
        super.onPause();
        JobsGithubApp.getApp().getNavigatorHolder().removeNavigator();
    }
}
