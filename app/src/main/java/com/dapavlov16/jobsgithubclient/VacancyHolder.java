package com.dapavlov16.jobsgithubclient;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

public class VacancyHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView companyTextView;
    private TextView locationTextView;
    //private ImageView logoImageView;

    public VacancyHolder(View view){
        super(view);
        titleTextView = view.findViewById(R.id.titleText);
        companyTextView = view.findViewById(R.id.companyText);
        locationTextView = view.findViewById(R.id.locationText);
    }

    public void bind(final Vacancy vacancy, final RecyclerViewAdapter.OnItemClickListener onItemClickListener){
        titleTextView.setText(vacancy.getTitle());
        companyTextView.setText(vacancy.getCompany());
        locationTextView.setText(vacancy.getLocation());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(vacancy);
            }
        });
    }
}
