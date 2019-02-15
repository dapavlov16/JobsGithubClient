package com.dapavlov16.jobsgithubclient.ui;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.dapavlov16.jobsgithubclient.R;
import com.dapavlov16.jobsgithubclient.model.Vacancy;

import androidx.recyclerview.widget.RecyclerView;

import static android.text.format.DateUtils.DAY_IN_MILLIS;
import static android.text.format.DateUtils.WEEK_IN_MILLIS;

public class VacancyHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView companyTextView;
    private TextView locationTextView;
    private TextView dateTextView;


    public VacancyHolder(View view) {
        super(view);
        titleTextView = view.findViewById(R.id.titleText);
        companyTextView = view.findViewById(R.id.companyText);
        locationTextView = view.findViewById(R.id.locationText);
        dateTextView = view.findViewById(R.id.dateText);
    }

    public void bind(final Vacancy vacancy, final RecyclerViewAdapter.OnItemClickListener onItemClickListener) {
        titleTextView.setText(vacancy.getTitle());
        companyTextView.setText(vacancy.getCompany());
        locationTextView.setText(vacancy.getLocation());
        try {
            //long diff = System.currentTimeMillis() - vacancy.getCreatedAt();
            //long format = diff >= 1000 * 60 * 60 * 24 * 7 ? WEEK_IN_MILLIS : DAY_IN_MILLIS;
            dateTextView.setText(DateUtils.getRelativeTimeSpanString(vacancy.getCreatedAt()));
        } catch (Exception ignore) {
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(vacancy);
            }
        });
    }
}
