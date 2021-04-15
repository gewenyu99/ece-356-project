package com.ece356.demographics.model;


import org.springframework.data.annotation.Id;

public class PopulationDist {

    @Id
    private String countryId;
    private long year;
    private long age;
    private long population;

    public PopulationDist(String countryId, int year, long age, long population) {
        this.countryId = countryId;
        this.year = year;
        this.age = age;
        this.population = population;
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


    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }


    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

}
