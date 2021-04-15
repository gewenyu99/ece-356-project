package com.ece356.demographics.model;


import org.springframework.data.annotation.Id;

public class PopulationDist5YearRange {

    @Id
    private String countryId;
    private long year;
    private long startAge;
    private long population;


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


    public long getStartAge() {
        return startAge;
    }

    public void setStartAge(long startAge) {
        this.startAge = startAge;
    }


    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

}
