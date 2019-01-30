package com.dapavlov16.jobsgithubclient;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class VacancyHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView companyTextView;
    private TextView descriptionTextView;
    //private ImageView logoImageView;

    public VacancyHolder(View view){
        super(view);
        titleTextView = view.findViewById(R.id.titleText);
        companyTextView = view.findViewById(R.id.companyText);
        descriptionTextView = view.findViewById(R.id.descriptionText);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view., VacancyActivity.class);
            }
        });
    }

    public void bind(Vacancy vacancy){
        titleTextView.setText(vacancy.getTitle());
        companyTextView.setText(vacancy.getCompany());
        descriptionTextView.setText(vacancy.getDescription());
    }
}
