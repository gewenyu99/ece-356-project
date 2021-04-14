package com.ece356.demographics.dao;

import com.ece356.demographics.model.Authority;
import com.ece356.demographics.model.QolData;
import com.ece356.demographics.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import com.ece356.demographics.SecurityConfig;
import java.util.List;

@RegisterBeanMapper(AuthorityDao.class)
public interface AuthorityDao {

    @Transaction
    @SqlUpdate("INSERT INTO authorities(username, authority) VALUES (:username, :role);")
    void createUserRole(@Bind("username") String username, @Bind("role") String role);

    @SqlQuery("SELECT * FROM authorities")
    @RegisterBeanMapper(Authority.class)
    List<Authority> getUsers();

    @SqlQuery("SELECT * FROM authorities WHERE username = :username")
    @RegisterBeanMapper(Authority.class)
    Authority getAuthority(@Bind("username") String username);

    @SqlUpdate("UPDATE authorities SET authority = :role WHERE username = :username")
    void setUserToRole(@Bind("username") String username, @Bind("role") String role);

    @SqlUpdate("DELETE FROM authorities WHERE username = :username")
    @RegisterBeanMapper(Authority.class)
    void deleteUser(@Bind("username") String username);
}

