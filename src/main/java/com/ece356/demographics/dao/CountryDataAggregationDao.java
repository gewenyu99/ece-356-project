package com.ece356.demographics.dao;

import com.ece356.demographics.model.CountryDataAggregation;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(CountryDataAggregation.class)
public interface CountryDataAggregationDao {

    @SqlQuery("SELECT * FROM country_data_aggregation ORDER BY country_id WHERE country_id in (<idList>);")
    List<CountryDataAggregation> listDataAggregation(@BindList("idList") List<String> idList);

    @SqlQuery("SELECT * FROM country_data_aggregation ORDER BY country_id")
    List<CountryDataAggregation> listDataAggregation();
}