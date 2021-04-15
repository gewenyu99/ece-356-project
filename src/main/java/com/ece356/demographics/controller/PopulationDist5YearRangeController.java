package com.ece356.demographics.controller;

import com.ece356.demographics.DemographicsApplication;
import com.ece356.demographics.dao.PopulationDist5YearRangeDao;
import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.model.PopulationDist;
import com.ece356.demographics.model.PopulationDist5YearRange;
import com.ece356.demographics.service.PopulationDist5YearRangeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class PopulationDist5YearRangeController {
    ObjectMapper objectMapper = new ObjectMapper();

    private Jdbi jdbi;
    private PopulationDist5YearRangeService populationDistService;

    public PopulationDist5YearRangeController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Autowired
    public void setPopulationDistService(PopulationDist5YearRangeService populationDistService) {
        this.populationDistService = populationDistService;
    }

    @RequestMapping(value = "/data/populationDist5YearRange", method = GET)
    public List<PopulationDist5YearRange> PopulationDist5YearRange(@RequestParam(value="countryIDs", required=false) String[] idList,
                                                                   @RequestParam(value="page", required=false, defaultValue = "1") int page) {
        if(idList == null){
            return jdbi.withExtension(PopulationDist5YearRangeDao.class, dao -> dao.getPopulationDist5YearRange((page-1)* DemographicsApplication.PAGE_SIZE ));
        }
        return jdbi.withExtension(PopulationDist5YearRangeDao.class, dao -> dao.getPopulationDist5YearRange( Arrays.asList(idList), (page-1) * DemographicsApplication.PAGE_SIZE));
    }

    @RequestMapping(value = "/data/populationDist5YearRange/{id}/{startYear}/{endYear}")
    public List<PopulationDist5YearRange> getPopulationDataBetween(@PathVariable String id, @PathVariable long startYear, @PathVariable long endYear) {
        return populationDistService.getPopulationDist5YearRangeBetween(id, startYear, endYear);
    }

    @PostMapping(value = "/create/populationDist5YearRange")
    public String create(@RequestBody PopulationDist5YearRange populationDist) {
        try {
            jdbi.useExtension(PopulationDist5YearRangeDao.class, dao -> dao.createPopulationDist5YearRange(populationDist.getCountryId(), populationDist.getYear(), populationDist.getStartAge(), populationDist.getPopulation()));
        } catch (Exception e) {
            return e.toString();
        }
        return "YOlo";
    }

    @PostMapping(value = "/update/populationDist5YearRange")
    public String update(@RequestBody PopulationDist5YearRange populationDist) {
        populationDistService.updatePopulationDist5YearRange(populationDist);
        return "Yeaeolo";
    }

    @RequestMapping("/delete/populationDist5YearRange/{id}/{year}")
    public String delete(@PathVariable String id, @PathVariable long year) {
        populationDistService.deletePopulationDist5YearRange(id, year);
        return "maybe delete populatioin dist";
    }
}

