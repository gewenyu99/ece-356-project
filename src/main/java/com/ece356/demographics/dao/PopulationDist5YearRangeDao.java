package com.ece356.demographics.dao;

import com.ece356.demographics.model.PopulationDist5YearRange;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(PopulationDist5YearRange.class)
public interface PopulationDist5YearRangeDao {
    @SqlQuery("SELECT * FROM PopulationDist_5YearRange WHERE countryID in (<idList>) ORDER BY year,startAge LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist5YearRange> getPopulationDist5YearRange(@BindList("idList") List<String> idList,
                                                               @Bind("pageOffset") int pageOffset);

    @SqlQuery("SELECT * FROM PopulationDist_5YearRange ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist5YearRange> getPopulationDist5YearRange(@Bind("pageOffset") int pageOffset);

}

