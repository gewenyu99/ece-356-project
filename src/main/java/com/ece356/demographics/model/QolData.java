package com.ece356.demographics.model;


import org.springframework.data.annotation.Id;

public class QolData {

    @Id
    private String countryId;
    private long year;
    private double hdi;
    private double lifeExpectancy;
    private double survivalToAge65;


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


    public double getHdi() {
        return hdi;
    }

    public void setHdi(double hdi) {
        this.hdi = hdi;
    }


    public double getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(double lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }


    public double getSurvivalToAge65() {
        return survivalToAge65;
    }

    public void setSurvivalToAge65(double survivalToAge65) {
        this.survivalToAge65 = survivalToAge65;
    }

}
