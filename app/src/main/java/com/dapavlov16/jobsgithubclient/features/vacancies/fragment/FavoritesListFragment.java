package com.dapavlov16.jobsgithubclient.features.vacancies.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dapavlov16.jobsgithubclient.R;
import com.dapavlov16.jobsgithubclient.core.BaseFragment;
import com.dapavlov16.jobsgithubclient.features.vacancies.viewmodel.FavoritesListViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

public class FavoritesListFragment extends BaseFragment {

    private FavoritesListViewModel viewModel;
    private SharedPreferences preferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(FavoritesListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_favorites_list, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(rootView.getContext());

        return rootView;
    }
}
