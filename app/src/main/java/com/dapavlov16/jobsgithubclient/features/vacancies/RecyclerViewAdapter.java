package com.dapavlov16.jobsgithubclient.features.vacancies;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dapavlov16.jobsgithubclient.R;
import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<VacancyHolder> {

    private List<Vacancy> vacancyList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private OnLoadMoreListener onLoadMoreListener;
    private int totalItemCount;
    private int lastVisibleItem;
    private int visibleThreshold = 15;
    private boolean isLoading = false;

    public RecyclerViewAdapter(RecyclerView recyclerView) {
        final LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if(!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)){
                    if (onLoadMoreListener != null){
                        isLoading = true;
                        onLoadMoreListener.onLoadMore();
                    }
                }
            }
        });
    }

    public void setItems(List<Vacancy> vacancyList, boolean refresh){
        if (refresh){
            this.vacancyList.clear();
        }
        this.vacancyList.addAll(vacancyList);
        isLoading = false;
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

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        if (onItemClickListener == null) {
            throw new IllegalArgumentException("onItemClickListener can't be null");
        }
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener){
        if (onLoadMoreListener == null) {
            throw new IllegalArgumentException("onLoadMoreListener can't be null");
        }
        this.onLoadMoreListener = onLoadMoreListener;
    }
}