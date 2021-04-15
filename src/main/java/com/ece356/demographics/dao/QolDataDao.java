package com.ece356.demographics.dao;

import com.ece356.demographics.model.QolData;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

@RegisterBeanMapper(QolDataDao.class)
public interface QolDataDao {
    @SqlQuery("SELECT * FROM qol_data WHERE countryID in (<idList>) ORDER BY year,age LIMIT 30 OFFSET :pageOffset;")
    List<QolData> getQolData(@BindList("idList") List<String> idList,
                             @Bind("pageOffset") int pageOffset);

    @SqlQuery("SELECT * FROM qol_data ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<QolData> getQolData(@Bind("pageOffset") int pageOffset);

    @Transaction
    @SqlUpdate("INSERT INTO qol_data(country_id, year, hdi, life_expectancy, survival_to_age65) VALUES(?,?,?,?,?);")
    void createQolData(String country_id, long year, double hdi, double life_expectancy, double survival_to_age65);
}

