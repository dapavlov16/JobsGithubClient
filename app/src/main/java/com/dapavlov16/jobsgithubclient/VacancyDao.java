package com.dapavlov16.jobsgithubclient;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface VacancyDao {

    @Insert
    void insertAll(Vacancy... vacancies);

    @Query("SELECT * FROM vacancy")
    List<Vacancy> getAllVacancy();
}
