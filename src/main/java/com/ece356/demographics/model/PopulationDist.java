package com.ece356.demographics.model;


import org.springframework.data.annotation.Id;

public class PopulationDist {

    @Id
    private String countryId;
    private long year;
    public long start_0;
    public long start_5;
    public long start_10;
    public long start_15;
    public long start_20;
    public long start_25;
    public long start_30;
    public long start_35;
    public long start_40;
    public long start_45;
    public long start_50;
    public long start_55;
    public long start_60;
    public long start_65;
    public long start_70;
    public long start_75;
    public long start_80;
    public long start_85;
    public long start_90;
    public long start_95;
    public long start_100;

    public PopulationDist(String countryId, int year,
                          long start_0, long start_5, long start_10, long start_15,
                          long start_20, long start_25, long start_30, long start_35,
                          long start_40, long start_45, long start_50, long start_55,
                          long start_60, long start_65, long start_70, long start_75,
                          long start_80, long start_85, long start_90, long start_95, long start_100) {
        this.countryId = countryId;
        this.year = year;
        this.start_0 = start_0;
        this.start_5 = start_5;
        this.start_10 = start_10;
        this.start_15 = start_15;
        this.start_20 = start_20;
        this.start_25 = start_25;
        this.start_30 = start_30;
        this.start_35 = start_35;
        this.start_40 = start_40;
        this.start_45 = start_45;
        this.start_50 = start_50;
        this.start_55 = start_55;
        this.start_60 = start_60;
        this.start_65 = start_65;
        this.start_70 = start_70;
        this.start_75 = start_75;
        this.start_80 = start_80;
        this.start_85 = start_85;
        this.start_90 = start_90;
        this.start_95 = start_95;
        this.start_100 = start_100;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }


    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }
}

