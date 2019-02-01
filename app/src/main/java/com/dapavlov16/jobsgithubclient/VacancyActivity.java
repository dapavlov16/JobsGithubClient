package com.dapavlov16.jobsgithubclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

public class VacancyActivity extends AppCompatActivity {

    private TextView title;
    private TextView company;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacancy);
        title = findViewById(R.id.vacancyTitle);
        company = findViewById(R.id.vacancyCompany);
        description = findViewById(R.id.vacancyDescription);

        onIntentReceived(getIntent().getExtras());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onIntentReceived(intent.getExtras());
    }


    private void onIntentReceived(Bundle bundle) {
        if (bundle == null || !bundle.containsKey(MainActivity.KEY_VACANCY)) {
            throw new IllegalArgumentException("bundle is null or vacancy data is null");
        }

        Vacancy vacancy = (Vacancy) bundle.getSerializable(MainActivity.KEY_VACANCY);

        title.setText(vacancy.getTitle());
        company.setText(vacancy.getCompany());
        description.setText(Html.fromHtml(vacancy.getDescription()).toString());
    }
}
