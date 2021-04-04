package com.ece356.demographics.controller;

import com.ece356.demographics.dao.CountryDao;
import com.ece356.demographics.model.Country;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class CountryController {
    ObjectMapper objectMapper = new ObjectMapper();

    private Jdbi jdbi;

    public CountryController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @RequestMapping(value = "/country", method = GET)
    public String index() {
        return "This endpoint is for countrys";
    }

    @RequestMapping(value = "/country/list", method = GET)
    public List<Country> list() {
        return jdbi.withExtension(CountryDao.class, CountryDao::listCountries);
    }
}

