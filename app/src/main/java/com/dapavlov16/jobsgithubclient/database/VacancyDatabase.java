package com.dapavlov16.jobsgithubclient.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

@Database(entities = {Vacancy.class}, version = 1)
public abstract class VacancyDatabase extends RoomDatabase {
    public abstract VacancyDao getVacancyDao();
}
