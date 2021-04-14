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
    @SqlUpdate("INSERT INTO users(username,password,enabled) VALUES(:username,:password,:isEnabled);")
    void createNormalUser(@Bind("username") String username, @Bind("password") String password, @Bind("isEnabled") int isEnabled);

    @Transaction
    @SqlUpdate("INSERT INTO users(username,password,enabled) VALUES(:username,:password,:enabled);")
    void createAdminUser(@BindBean User user);

    @SqlQuery("SELECT username, enabled FROM users WHERE enabled = 1")
    @RegisterBeanMapper(User.class)
    List<User> getUsers();

    @SqlQuery("SELECT * FROM users WHERE username = :username")
    @RegisterBeanMapper(User.class)
    User getUser(@Bind("username") String username);

    @SqlUpdate("UPDATE users SET enabled = 1 WHERE username = :username")
    @RegisterBeanMapper(User.class)
    void enableUser(@Bind("username") String username);

    @SqlUpdate("UPDATE users SET enabled = 0 WHERE username = :username")
    @RegisterBeanMapper(User.class)
    void disableUser(@Bind("username") String username);

    @SqlUpdate("UPDATE users SET password = :password WHERE username = :username")
    @RegisterBeanMapper(User.class)
    void updateUserPassword(@Bind("username") String username, @Bind("password") String password);


}

