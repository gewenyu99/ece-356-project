package com.ece356.demographics.controller;

import com.ece356.demographics.dao.CountryDao;
import com.ece356.demographics.model.Country;
import com.ece356.demographics.repository.CountryRepository;
import com.ece356.demographics.service.CountryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@RestController
public class CountryController {
    ObjectMapper objectMapper = new ObjectMapper();

    private Jdbi jdbi;
    private CountryService countryService;

    @Autowired
    CountryRepository countryRepository;

    public CountryController(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    @RequestMapping(value = "/data/country", method = GET)
    public String index() {
        return "This endpoint is for countrys";
    }

    @RequestMapping(value = "/data/country/list", method = GET)
    public List<Country> list() {
        return jdbi.withExtension(CountryDao.class, CountryDao::listCountries);
    }

    @PostMapping(value = "/create/country")
    public String create(@RequestBody Country country) {
        try {
            jdbi.useExtension(CountryDao.class, dao -> dao.createCountry(country.getCountryID(), country.getCountryName()));
            //countryService.addCountry(country);
        } catch (Exception e) {
            return e.toString();
        }
        return "YOlo";
    }

    @PostMapping(value = "/update/country")
    public String update(@RequestBody Country country) {
        countryService.updateCountry(country);
        return "Yeaeolo";
    }

    @RequestMapping("/delete/country/{id}")
    public String delete(@PathVariable String id) {
        countryService.deleteCountry(id);
        return "maybe delete country";
    }
}

