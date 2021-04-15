package com.ece356.demographics.controller;

import com.ece356.demographics.DemographicsApplication;
import com.ece356.demographics.dao.CountryDao;
import com.ece356.demographics.dao.PopulationDataDao;
import com.ece356.demographics.dao.PopulationDistDao;
import com.ece356.demographics.model.Country;
import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.model.PopulationDist;
import com.ece356.demographics.model.PopulationDist5YearRange;
import com.ece356.demographics.service.CountryService;
import com.ece356.demographics.service.PopulationDistService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class PopulationDistController {
    ObjectMapper objectMapper = new ObjectMapper();

    private Jdbi jdbi;
    private PopulationDistService populationDistService;

    public PopulationDistController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Autowired
    public void setPopulationDistService(PopulationDistService populationDistService) {
        this.populationDistService = populationDistService;
    }

    @RequestMapping(value = "/data/populationDist", method = GET)
    public List<PopulationDist> populationDist(@RequestParam(value="countryIDs", required=false) String[] idList,
                                               @RequestParam(value="page", required=false, defaultValue = "1") int page) {
        if(idList == null){
            return jdbi.withExtension(PopulationDistDao.class, dao -> dao.getPopulationDist((page-1)* DemographicsApplication.PAGE_SIZE ));
        }
        return jdbi.withExtension(PopulationDistDao.class, dao -> dao.getPopulationDist( Arrays.asList(idList), (page-1) * DemographicsApplication.PAGE_SIZE));
    }

    @RequestMapping(value = "/data/populationDist/{id}/{startYear}/{endYear}")
    public List<PopulationDist> getPopulationDistBetween(@PathVariable String id, @PathVariable long startYear, @PathVariable long endYear) {
        return populationDistService.getPopulationDistBetween(id, startYear, endYear);
    }


    @PostMapping(value = "/create/populationDist")
    public String create(@RequestBody PopulationDist populationDist) {
        try {
            jdbi.useExtension(PopulationDistDao.class, dao -> dao.createPopulationDist(populationDist.getCountryId(), populationDist.getYear(), populationDist.getAge(), populationDist.getPopulation()));
        } catch (Exception e) {
            return e.toString();
        }
        return "YOlo";
    }

    @PostMapping(value = "/update/populationDist")
    public String update(@RequestBody PopulationDist populationDist) {
        populationDistService.updatePopulationDist(populationDist);
        return "Yeaeolo";
    }

    @RequestMapping("/delete/populationDist/{id}/{year}")
    public String delete(@PathVariable String id, @PathVariable long year) {
        populationDistService.deletePopulationDist(id, year);
        return "maybe delete populatioin dist";
    }
}

