package com.ece356.demographics.dao;

import com.ece356.demographics.model.QolData;
import com.ece356.demographics.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.BindList;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;

import java.util.List;

@RegisterBeanMapper(UserDao.class)
public interface UserDao {

    @Transaction
    @SqlUpdate("INSERT INTO User(username,password,isAdmin) VALUES(:username,:password,:isAdmin)")
    void createUser(@BindBean User user);

    @SqlQuery("SELECT * FROM User")
    @RegisterBeanMapper(User.class)
    List<User> getUsers();

    @SqlQuery("SELECT * FROM User WHERE username = :username")
    @RegisterBeanMapper(User.class)
    User getUser(@Bind("username") String username);

}

