package com.ece356.demographics.controller;

import com.ece356.demographics.DemographicsApplication;
import com.ece356.demographics.dao.PopulationDist5YearRangeDao;
import com.ece356.demographics.dao.PopulationDistDao;
import com.ece356.demographics.model.PopulationDist;
import com.ece356.demographics.model.PopulationDist5YearRange;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class PopulationDist5YearRangeController {
    ObjectMapper objectMapper = new ObjectMapper();

    private Jdbi jdbi;

    public PopulationDist5YearRangeController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @RequestMapping(value = "/populationDist5YearRange", method = GET)
    public List<PopulationDist5YearRange> PopulationDist5YearRange(@RequestParam(value="countryIDs", required=false) String[] idList,
                                                                   @RequestParam(value="page", required=false, defaultValue = "1") int page) {
        if(idList == null){
            return jdbi.withExtension(PopulationDist5YearRangeDao.class, dao -> dao.getPopulationDist5YearRange((page-1)* DemographicsApplication.PAGE_SIZE ));
        }
        return jdbi.withExtension(PopulationDist5YearRangeDao.class, dao -> dao.getPopulationDist5YearRange( Arrays.asList(idList), (page-1) * DemographicsApplication.PAGE_SIZE));
    }
}

