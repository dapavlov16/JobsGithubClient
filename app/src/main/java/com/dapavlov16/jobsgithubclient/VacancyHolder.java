package com.dapavlov16.jobsgithubclient;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

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
    }

    public void bind(final Vacancy vacancy, final RecyclerViewAdapter.OnItemClickListener onItemClickListener){
        titleTextView.setText(vacancy.getTitle());
        companyTextView.setText(vacancy.getCompany());
        descriptionTextView.setText(vacancy.getDescription().substring(0, 10));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(vacancy);
            }
        });
    }
}
