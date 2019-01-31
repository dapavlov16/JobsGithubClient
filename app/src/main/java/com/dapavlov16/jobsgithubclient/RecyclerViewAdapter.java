package com.dapavlov16.jobsgithubclient;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<VacancyHolder> {

    private List<Vacancy> vacancyList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private int totalItemCount;
    private int lastVisibleItem;
    private int visibleThreshold = 10;

    public RecyclerViewAdapter(OnItemClickListener onItemClickListener, RecyclerView recyclerView) {
        if (onItemClickListener == null) {
            throw new IllegalArgumentException("onItemClickListener can't be null");
        }
        this.onItemClickListener = onItemClickListener;
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if(totalItemCount <= (lastVisibleItem + visibleThreshold)){
                    if (onLoadMoreListener != null){
                        onLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }

    public void setItems(List<Vacancy> vacancyList){
        this.vacancyList.addAll(vacancyList);
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

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        this.onLoadMoreListener = onLoadMoreListener;
    }
}