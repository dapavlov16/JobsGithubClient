package com.dapavlov16.jobsgithubclient.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.dapavlov16.jobsgithubclient.model.Vacancy;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface VacancyDao {

    @Insert
    void insertAll(List<Vacancy> vacancies);

    @Query("SELECT * FROM vacancy")
    Single<List<Vacancy>> getAllVacancy();
}
