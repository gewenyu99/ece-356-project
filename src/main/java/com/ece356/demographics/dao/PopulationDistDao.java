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
    @SqlQuery("SELECT * FROM population_dist WHERE country_id in (<idList>) ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist> getPopulationDist(@BindList("idList") List<String> idList,
                                           @Bind("pageOffset") int pageOffset);

    @SqlQuery("SELECT * FROM population_dist ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist> getPopulationDist(@Bind("pageOffset") int pageOffset);

    @Transaction
    @SqlUpdate("INSERT INTO population_dist(country_id, year, start_0, start_5, start_10, start_15, start_20, start_25, start_30, start_35, start_40, start_45, start_50, start_55, start_60, start_65, start_70, start_75, start_80, start_85, start_90, start_95, start_100) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);")
    void createPopulationDist(String country_id, long year,
                              long start_0, long start_5, long start_10, long start_15,
                              long start_20, long start_25, long start_30, long start_35,
                              long start_40, long start_45, long start_50, long start_55,
                              long start_60, long start_65, long start_70, long start_75,
                              long start_80, long start_85, long start_90, long start_95,
                              long start_100);
}

