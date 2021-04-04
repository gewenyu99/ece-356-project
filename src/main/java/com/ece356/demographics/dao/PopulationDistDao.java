package com.ece356.demographics.dao;

import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.model.PopulationDist;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(PopulationDist.class)
public interface PopulationDistDao {
    @SqlQuery("SELECT * FROM PopulationDist WHERE countryID in (<idList>) ORDER BY year,age LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist> getPopulationDist(@BindList("idList") List<String> idList,
                                           @Bind("pageOffset") int pageOffset);
    @SqlQuery("SELECT * FROM PopulationDist ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationDist> getPopulationDist(@Bind("pageOffset") int pageOffset);

}

