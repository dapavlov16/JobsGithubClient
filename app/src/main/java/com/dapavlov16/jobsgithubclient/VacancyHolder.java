package com.dapavlov16.jobsgithubclient;

import android.icu.text.SimpleDateFormat;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.text.ParseException;
import java.util.Calendar;

public class VacancyHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView companyTextView;
    private TextView locationTextView;
    private TextView dateTextView;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");


    public VacancyHolder(View view){
        super(view);
        titleTextView = view.findViewById(R.id.titleText);
        companyTextView = view.findViewById(R.id.companyText);
        locationTextView = view.findViewById(R.id.locationText);
        dateTextView = view.findViewById(R.id.dateText);
    }

    public void bind(final Vacancy vacancy, final RecyclerViewAdapter.OnItemClickListener onItemClickListener){
        titleTextView.setText(vacancy.getTitle());
        companyTextView.setText(vacancy.getCompany());
        locationTextView.setText(vacancy.getLocation());
        try {
            dateTextView.setText(DateUtils.getRelativeTimeSpanString(dateFormat.parse(vacancy.getCreated_at()).getTime(), Calendar.getInstance().getTimeInMillis(), DateUtils.MINUTE_IN_MILLIS));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(vacancy);
            }
        });
    }
}
