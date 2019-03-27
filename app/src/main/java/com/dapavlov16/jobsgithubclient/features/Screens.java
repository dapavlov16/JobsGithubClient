package com.dapavlov16.jobsgithubclient.features;

import com.dapavlov16.jobsgithubclient.features.vacancies.fragment.FavoritesListFragment;
import com.dapavlov16.jobsgithubclient.features.vacancies.fragment.VacanciesListFragment;

import androidx.fragment.app.Fragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static final class VacanciesListScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return new VacanciesListFragment();
        }
    }

    public static final class FavoritesListScreen extends SupportAppScreen {
        @Override
        public Fragment getFragment() {
            return new FavoritesListFragment();
        }
    }
}
