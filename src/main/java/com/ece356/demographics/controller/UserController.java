package com.ece356.demographics.controller;

import com.ece356.demographics.DemographicsApplication;
import com.ece356.demographics.dao.QolDataDao;
import com.ece356.demographics.model.QolData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}

