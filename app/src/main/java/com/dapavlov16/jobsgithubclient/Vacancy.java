package com.dapavlov16.jobsgithubclient;

public class Vacancy {
    //private String id;
    //private String url;
    private String title;
    private String company;
    private String description;

    public Vacancy(String title, String company, String description){
        this.title = title;
        this.company = company;
        this.description = description;
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
