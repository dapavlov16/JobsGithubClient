package com.dapavlov16.jobsgithubclient.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

@Database(entities = {Vacancy.class}, version = 1)
public abstract class VacancyDatabase extends RoomDatabase {
    public abstract VacancyDao getVacancyDao();
}
