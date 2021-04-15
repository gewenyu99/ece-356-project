package com.ece356.demographics.dao;

import com.ece356.demographics.model.PopulationData;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

@RegisterBeanMapper(PopulationData.class)
public interface PopulationDataDao {
    @SqlQuery("SELECT * FROM population_data WHERE country_id in (<idList>) ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationData> getPopulationData(@BindList("idList") List<String> idList,
                                           @Bind("pageOffset") int pageOffset);
    @SqlQuery("SELECT * FROM population_data ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<PopulationData> getPopulationData(@Bind("pageOffset") int pageOffset);

    @Transaction
    @SqlUpdate("INSERT INTO population_data(country_id, year, birth_rate, death_rate, infant_mortality_rate, total_population) VALUES(?,?,?,?,?,?);")
    void createPopulationData(String country_id, long year, double birth_rate, double death_rate, double infant_mortality_rate, long population);
}

