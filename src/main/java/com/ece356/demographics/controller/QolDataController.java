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
public class QolDataController {
    ObjectMapper objectMapper = new ObjectMapper();

    private final Jdbi jdbi;

    public QolDataController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @RequestMapping(value = "/qolData", method = GET)
    public List<QolData> qolData(@RequestParam(value = "countryIDs", required = false) String[] idList,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") int page) {
        if (idList == null) {
            return jdbi.withExtension(QolDataDao.class, dao -> dao.getQolData((page - 1) * DemographicsApplication.PAGE_SIZE));
        }
        return jdbi.withExtension(QolDataDao.class, dao -> dao.getQolData(Arrays.asList(idList), (page - 1) * DemographicsApplication.PAGE_SIZE));
    }
}

