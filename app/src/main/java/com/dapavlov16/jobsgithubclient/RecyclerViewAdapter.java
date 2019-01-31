package com.dapavlov16.jobsgithubclient;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<VacancyHolder> {

    private List<Vacancy> vacancyList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public RecyclerViewAdapter(OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            throw new IllegalArgumentException("onItemClickListener cann't be null");
        }
        this.onItemClickListener = onItemClickListener;
    }

    public void setItems(List<Vacancy> vacancyList){
        this.vacancyList = vacancyList;
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
        vacancyHolder.bind(vacancyList.get(i), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return vacancyList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Vacancy item);
    }
}