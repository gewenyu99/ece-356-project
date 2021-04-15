package com.ece356.demographics.dao;

import com.ece356.demographics.model.PopulationDist;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

@RegisterBeanMapper(PopulationDist.class)
public interface PopulationDistDao {
    @SqlQuery("SELECT * FROM population_dist WHERE country_id in (<idList>) ORDER BY year,age LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist> getPopulationDist(@BindList("idList") List<String> idList,
                                           @Bind("pageOffset") int pageOffset);

    @SqlQuery("SELECT * FROM population_dist ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist> getPopulationDist(@Bind("pageOffset") int pageOffset);

    @Transaction
    @SqlUpdate("INSERT INTO population_dist(country_id, year, age, population) VALUES(?,?,?,?);")
    void createPopulationDist(String country_id, long year, long age, long population);
}

