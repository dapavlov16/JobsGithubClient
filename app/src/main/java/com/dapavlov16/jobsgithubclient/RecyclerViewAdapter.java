package com.dapavlov16.jobsgithubclient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<VacancyHolder> {

    private List<Vacancy> vacancyList;

    public RecyclerViewAdapter(/*List<Vacancy> vacancyList*/){
        this.vacancyList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            vacancyList.add(new Vacancy("title" + i, "company " + i, "description" + "test" + 1));
        }
    }

    @NonNull
    @Override
    public VacancyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.item_vacancy, viewGroup, false);
        return new VacancyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VacancyHolder vacancyHolder, int i) {
        vacancyHolder.bind(vacancyList.get(i));
    }

    @Override
    public int getItemCount() {
        return vacancyList.size();
    }
}