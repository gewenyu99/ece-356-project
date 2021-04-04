package com.ece356.demographics.model;


public class PopulationData {

    private String countryId;
    private long year;
    private double birthRate;
    private double deathRate;
    private double infantMortalityRate;
    private long totalPopulation;


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


    public double getBirthRate() {
        return birthRate;
    }

    public void setBirthRate(double birthRate) {
        this.birthRate = birthRate;
    }


    public double getDeathRate() {
        return deathRate;
    }

    public void setDeathRate(double deathRate) {
        this.deathRate = deathRate;
    }


    public double getInfantMortalityRate() {
        return infantMortalityRate;
    }

    public void setInfantMortalityRate(double infantMortalityRate) {
        this.infantMortalityRate = infantMortalityRate;
    }


    public long getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(long totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

}
