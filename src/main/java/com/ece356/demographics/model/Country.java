package com.ece356.demographics.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public class Country {

    // must keep column names lower case: https://github.com/spring-projects/spring-boot/issues/2129
    // this anti-feature causes some problems...
    // more info on this problem: https://stackoverflow.com/questions/25283198/spring-boot-jpa-column-name-annotation-ignored/26546541#26546541

    // crudrepository really doesn't like creating things with existing ids
    // second solution: https://stackoverflow.com/questions/38893831/spring-data-crudrepositorys-save-method-and-update
    // We cannot go through the the service layer because our database is so.. so bad.

    @Id
    private String countryId;
    private String countryName;

    public Country(String countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    public String getCountryID() {
        return countryId;
    }

    public void setCountryId(String countryID) {
        this.countryId = countryID;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
