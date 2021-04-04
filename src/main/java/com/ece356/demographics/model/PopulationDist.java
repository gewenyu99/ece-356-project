package com.ece356.demographics.model;


public class PopulationDist {

    private String countryId;
    private long year;
    private long age;
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
