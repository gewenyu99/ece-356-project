package com.ece356.demographics.controller;

import com.ece356.demographics.DemographicsApplication;
import com.ece356.demographics.dao.PopulationDataDao;
import com.ece356.demographics.dao.PopulationDistDao;
import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.model.PopulationDist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class PopulationDistController {
    ObjectMapper objectMapper = new ObjectMapper();

    private Jdbi jdbi;

    public PopulationDistController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @RequestMapping(value = "/populationDist", method = GET)
    public List<PopulationDist> populationDist(@RequestParam(value="countryIDs", required=false) String[] idList,
                                               @RequestParam(value="page", required=false, defaultValue = "1") int page) {
        if(idList == null){
            return jdbi.withExtension(PopulationDistDao.class, dao -> dao.getPopulationDist((page-1)* DemographicsApplication.PAGE_SIZE ));
        }
        return jdbi.withExtension(PopulationDistDao.class, dao -> dao.getPopulationDist( Arrays.asList(idList), (page-1) * DemographicsApplication.PAGE_SIZE));
    }
}

