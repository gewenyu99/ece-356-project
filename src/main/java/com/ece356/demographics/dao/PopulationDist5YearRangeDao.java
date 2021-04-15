package com.ece356.demographics.dao;

import com.ece356.demographics.model.PopulationDist5YearRange;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

@RegisterBeanMapper(PopulationDist5YearRange.class)
public interface PopulationDist5YearRangeDao {
    @SqlQuery("SELECT * FROM population_dist5_year_range WHERE country_id in (<idList>) ORDER BY year,start_age LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist5YearRange> getPopulationDist5YearRange(@BindList("idList") List<String> idList,
                                                               @Bind("pageOffset") int pageOffset);

    @SqlQuery("SELECT * FROM population_dist5_year_range ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist5YearRange> getPopulationDist5YearRange(@Bind("pageOffset") int pageOffset);

    @Transaction
    @SqlUpdate("INSERT INTO population_dist5_year_range(country_id, year, start_age, population) VALUES(?,?,?,?);")
    void createPopulationDist5YearRange(String country_id, long year, long start_age, long population);
}

