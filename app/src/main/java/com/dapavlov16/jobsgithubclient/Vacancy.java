package com.dapavlov16.jobsgithubclient;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Vacancy {
    @PrimaryKey private String id;
    //private String url;
    private String title;
    private String company;
    private String description;

    public Vacancy(String id, String title, String company, String description){
        this.id = id;
        this.title = title;
        this.company = company;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
