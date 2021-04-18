package com.ece356.demographics.controller;

import com.ece356.demographics.DemographicsApplication;
import com.ece356.demographics.dao.QolDataDao;
import com.ece356.demographics.model.QolData;
import com.ece356.demographics.service.QolDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class QolDataController {
    ObjectMapper objectMapper = new ObjectMapper();

    private final Jdbi jdbi;
    private QolDataService qolDataService;

    public QolDataController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Autowired
    public void setQolDataService(QolDataService qolDataService) {
        this.qolDataService = qolDataService;
    }

    @RequestMapping(value = "/data/qolData", method = GET)
    public List<QolData> qolData(@RequestParam(value = "countryIDs", required = false) String[] idList,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        if (idList == null) {
            return jdbi.withExtension(QolDataDao.class, dao -> dao.getQolData((page - 1) * DemographicsApplication.PAGE_SIZE));
        }
        return jdbi.withExtension(QolDataDao.class, dao -> dao.getQolData(Arrays.asList(idList), (page - 1) * DemographicsApplication.PAGE_SIZE));
    }

    @RequestMapping(value = "/data/qolData/{id}/{startYear}/{endYear}")
    public List<QolData> getQolDataBetween(@PathVariable String id, @PathVariable long startYear, @PathVariable long endYear) {
        return qolDataService.getQolDataBetween(id, startYear, endYear);
    }


    @PostMapping(value = "/create/qolData")
    public String create(@RequestBody QolData qolData) {
        try {
            jdbi.useExtension(QolDataDao.class, dao -> dao.createQolData(qolData.getCountryId(), qolData.getYear(), qolData.getHdi(), qolData.getLifeExpectancy(), qolData.getSurvivalToAge65()));
        } catch (Exception e) {
            return e.toString();
        }
        return "YOlo";
    }

    @PostMapping(value = "/update/qolData")
    public String update(@RequestBody QolData qolData) {
        qolDataService.updateQolData(qolData);
        return "Yeaeolo";
    }

    @RequestMapping("/delete/qolData/{id}/{year}")
    public String delete(@PathVariable String id, @PathVariable long year) {
        qolDataService.deleteQolData(id, year);
        return "maybe delete qol data";
    }
}

