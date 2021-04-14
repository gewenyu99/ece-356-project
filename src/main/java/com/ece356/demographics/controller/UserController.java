package com.ece356.demographics.controller;

import com.ece356.demographics.DemographicsApplication;

import com.ece356.demographics.dao.AuthorityDao;
import com.ece356.demographics.dao.UserDao;
import com.ece356.demographics.model.User;
import com.ece356.demographics.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class UserController {
    ObjectMapper objectMapper = new ObjectMapper();

    private final Jdbi jdbi;

    public UserController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @RequestMapping(value = "/users", method = GET)
    public String index() {
        return "This endpoint is for users";
    }

    @RequestMapping(value = "/users/list", method = GET)
    public List<User> list() {
        return jdbi.withExtension(UserDao.class, UserDao::getUsers);
    }

    @RequestMapping(value = "/users/createNormalUser", method = GET)
    public String createNormalUser(@RequestParam(value="username") String username,
                                 @RequestParam(value="password") String password,
                                 @RequestParam(value="isEnabled", required = false, defaultValue = "1") int isEnabled) {

        String encodedPassword = SecurityConfig.passwordEncoder().encode(password);
        jdbi.useExtension(UserDao.class, dao -> dao.createNormalUser(username, encodedPassword, isEnabled));
        jdbi.useExtension(AuthorityDao.class, dao -> dao.createUserRole(username, "ROLE_USER"));
        return "Done";
    }
}

