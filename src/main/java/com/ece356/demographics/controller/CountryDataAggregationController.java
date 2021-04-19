package com.ece356.demographics.controller;

import com.ece356.demographics.dao.CountryDataAggregationDao;
import com.ece356.demographics.model.CountryDataAggregation;
import com.ece356.demographics.repository.CountryRepository;
import com.ece356.demographics.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class CountryDataAggregationController {
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    CountryRepository countryRepository;
    private Jdbi jdbi;
    private CountryService countryService;

    public CountryDataAggregationController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/data/country_data_aggre/", method = GET)
    public List<CountryDataAggregation> list() {
        return jdbi.withExtension(CountryDataAggregationDao.class, CountryDataAggregationDao::listDataAggregation);
    }

    @RequestMapping(value = "/data/country_data_aggre/list/", method = GET)
    public List<CountryDataAggregation> list(@RequestParam(value = "countryIDs") String[] idList) {
        return jdbi.withExtension(CountryDataAggregationDao.class, dao -> dao.listDataAggregation(Arrays.asList(idList)));
    }
}

