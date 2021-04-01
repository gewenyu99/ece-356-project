package com.ece356.demographics.dao;

import com.ece356.demographics.model.Country;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(Country.class)
public interface CountryDao {
    @SqlQuery("SELECT * FROM Country ORDER BY countryID")
    List<Country> listCountries();
}

