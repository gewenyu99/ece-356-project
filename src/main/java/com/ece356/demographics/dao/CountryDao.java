package com.ece356.demographics.dao;

import com.ece356.demographics.model.Country;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RegisterBeanMapper(Country.class)
public interface CountryDao {

    @Transaction
    @SqlUpdate("INSERT INTO country(country_id,country_name) VALUES(?,?);")
    void createCountry(String country_id, String country_name);

    @Transaction
    @SqlUpdate("DELETE FROM country WHERE country_id=?")
    void deleteCountry(String countryID);

    @Transaction
    @SqlUpdate("UPDATE country SET country_name=:countryName WHERE country_id=:countryID")
    void updateCountry(@Bind("countryID") String countryID, @Bind("countryName") String countryName);

    @SqlQuery("SELECT * FROM country ORDER BY country_id")
    List<Country> listCountries();
}