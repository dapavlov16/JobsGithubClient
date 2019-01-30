package com.dapavlov16.jobsgithubclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
    }
}
