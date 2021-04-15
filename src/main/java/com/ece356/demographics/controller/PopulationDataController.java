package com.ece356.demographics.controller;

import com.ece356.demographics.DemographicsApplication;
import com.ece356.demographics.dao.PopulationDataDao;
import com.ece356.demographics.dao.QolDataDao;
import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.model.QolData;
import com.ece356.demographics.service.PopulationDataService;
import com.ece356.demographics.service.QolDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class PopulationDataController {
    ObjectMapper objectMapper = new ObjectMapper();

    private Jdbi jdbi;
    private PopulationDataService populationDataService;

    public PopulationDataController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Autowired
    public void setPopulationDataService(PopulationDataService populationDataService) {
        this.populationDataService = populationDataService;
    }

    @RequestMapping(value = "/data/populationData", method = GET)
    public List<PopulationData> populationData(@RequestParam(value="countryIDs", required=false) String[] idList,
                                               @RequestParam(value="page", required=false, defaultValue = "1") int page) {
        if(idList == null){
            return jdbi.withExtension(PopulationDataDao.class, dao -> dao.getPopulationData((page-1)* DemographicsApplication.PAGE_SIZE ));
        }
        return jdbi.withExtension(PopulationDataDao.class, dao -> dao.getPopulationData( Arrays.asList(idList), (page-1) * DemographicsApplication.PAGE_SIZE));
    }

    @RequestMapping(value = "/data/populationData/{id}/{startYear}/{endYear}")
    public List<PopulationData> getPopulationDataBetween(@PathVariable String id, @PathVariable long startYear, @PathVariable long endYear) {
        return populationDataService.getPopulationDataBetween(id, startYear, endYear);
    }

    @PostMapping(value = "/create/populationData")
    public String create(@RequestBody PopulationData populationData) {
        try {
            jdbi.useExtension(PopulationDataDao.class, dao -> dao.createPopulationData(populationData.getCountryId(), populationData.getYear(), populationData.getBirthRate(), populationData.getDeathRate(), populationData.getInfantMortalityRate(), populationData.getTotalPopulation()));
        } catch (Exception e) {
            return e.toString();
        }
        return "YOlo";
    }

    @PostMapping(value = "/update/populationData")
    public String update(@RequestBody PopulationData populationData) {
        populationDataService.updatePopulationData(populationData);
        return "Yeaeolo";
    }

    @RequestMapping("/delete/populationData/{id}/{year}")
    public String delete(@PathVariable String id, @PathVariable long year) {
        populationDataService.deletePopulationData(id, year);
        return "maybe delete population data";
    }
}

