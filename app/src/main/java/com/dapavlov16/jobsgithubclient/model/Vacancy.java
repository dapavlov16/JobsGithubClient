package com.dapavlov16.jobsgithubclient.model;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import com.dapavlov16.jobsgithubclient.network.DateTimeAdapter;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Vacancy implements Serializable {
    @PrimaryKey
    @NonNull
    private String id;
    private String type;
    private String url;

    @SerializedName("created_at")
    @JsonAdapter(DateTimeAdapter.class)
    private Long createdAt;
    private String company;

    @SerializedName("company_url")
    private String companyUrl;
    private String location;
    private String title;
    private String description;

    @SerializedName("how_to_apply")
    private String howToApply;

    @SerializedName("company_logo")
    private String companyLogo;

    public Vacancy(@NonNull String id, Long createdAt, String title, String company, String description) {
        this.id = id;
        this.createdAt = createdAt;
        this.title = title;
        this.company = company;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getCompanyUrl() {
        return companyUrl;
    }

    public void setCompanyUrl(String companyUrl) {
        this.companyUrl = companyUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHowToApply() {
        return howToApply;
    }

    public void setHowToApply(String howToApply) {
        this.howToApply = howToApply;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public @NonNull
    String getId() {
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
