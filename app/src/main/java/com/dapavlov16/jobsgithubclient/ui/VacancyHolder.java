package com.dapavlov16.jobsgithubclient.ui;

import android.icu.text.SimpleDateFormat;
import android.view.View;
import android.widget.TextView;

import com.dapavlov16.jobsgithubclient.R;
import com.dapavlov16.jobsgithubclient.model.Vacancy;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.chrono.IsoChronology;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;
import org.threeten.bp.format.ResolverStyle;
import org.threeten.bp.format.SignStyle;
import org.threeten.bp.temporal.ChronoField;

import androidx.recyclerview.widget.RecyclerView;

public class VacancyHolder extends RecyclerView.ViewHolder {

    private TextView titleTextView;
    private TextView companyTextView;
    private TextView locationTextView;
    private TextView dateTextView;
    /*private LocalDateTime dateTime = new LocalDateTime()*/


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

            dateTextView.setText(ZonedDateTime.parse(vacancy.getCreatedAt()).toLocalDateTime().toString());
            //LocalDateTime dateTime = LocalDateTime.parse(vacancy.getCreatedAt(), DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss yyyy"));
        } catch (Exception ignore){}
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(vacancy);
            }
        });
    }
}
