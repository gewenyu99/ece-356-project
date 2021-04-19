package com.ece356.demographics.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

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

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

}
