package com.ece356.demographics.dao;

import com.ece356.demographics.model.QolData;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@RegisterBeanMapper(QolDataDao.class)
public interface QolDataDao {
    @SqlQuery("SELECT * FROM QolData WHERE countryID in (<idList>) ORDER BY year,age LIMIT 30 OFFSET :pageOffset;")
    List<QolData> getQolData(@BindList("idList") List<String> idList,
                             @Bind("pageOffset") int pageOffset);

    @SqlQuery("SELECT * FROM QolData ORDER BY year LIMIT 30 OFFSET :pageOffset;")
    List<QolData> getQolData(@Bind("pageOffset") int pageOffset);

}

