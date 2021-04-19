package com.ece356.demographics.service;

import com.ece356.demographics.model.Country;
import com.ece356.demographics.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;

    public Country getCountry(String id) {
        Optional<Country> countryResponse = countryRepository.findById(id);
        return countryResponse.orElse(null);
    }

    public Country updateCountry(Country country) {
        String countryID = country.getCountryId();
        Country findCountry = getCountry(countryID);
        findCountry.setCountryName(country.getCountryName());
        return countryRepository.save(findCountry);
    }

    public Country updateCountry(String countryID, String countryName) {
        Country country = getCountry(countryID);
        country.setCountryName(countryName);
        return countryRepository.save(country);
    }

    public void deleteCountry(String countryID) {
        Country country = getCountry(countryID);
        countryRepository.delete(country);
    }
}