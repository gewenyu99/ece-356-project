package com.ece356.demographics.controller;

import com.ece356.demographics.DemographicsApplication;
import com.ece356.demographics.dao.PopulationDataDao;
import com.ece356.demographics.model.PopulationData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class PopulationDataController {
    ObjectMapper objectMapper = new ObjectMapper();

    private Jdbi jdbi;

    public PopulationDataController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @RequestMapping(value = "/populationData", method = GET)
    public List<PopulationData> populationData(@RequestParam(value="countryIDs", required=false) String[] idList,
                                               @RequestParam(value="page", required=false, defaultValue = "1") int page) {
        if(idList == null){
            return jdbi.withExtension(PopulationDataDao.class, dao -> dao.getPopulationData((page-1)* DemographicsApplication.PAGE_SIZE ));
        }
        return jdbi.withExtension(PopulationDataDao.class, dao -> dao.getPopulationData( Arrays.asList(idList), (page-1) * DemographicsApplication.PAGE_SIZE));
    }
}

