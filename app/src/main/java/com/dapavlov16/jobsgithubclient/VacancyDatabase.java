package com.dapavlov16.jobsgithubclient;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Vacancy.class}, version = 1)
public abstract class VacancyDatabase extends RoomDatabase {
    public abstract VacancyDao getVacancyDao();
}
