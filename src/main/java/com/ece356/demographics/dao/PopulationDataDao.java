package com.ece356.demographics.dao;

import com.ece356.demographics.model.PopulationData;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(PopulationData.class)
public interface PopulationDataDao {
    @SqlQuery("SELECT * FROM PopulationData WHERE countryID in (<idList>) ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationData> getPopulationData(@BindList("idList") List<String> idList,
                                           @Bind("pageOffset") int pageOffset);
    @SqlQuery("SELECT * FROM PopulationData ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationData> getPopulationData(@Bind("pageOffset") int pageOffset);
}

