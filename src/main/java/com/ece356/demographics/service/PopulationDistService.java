package com.ece356.demographics.service;

import com.ece356.demographics.model.Country;
import com.ece356.demographics.model.PopulationDist;
import com.ece356.demographics.repository.CountryRepository;
import com.ece356.demographics.repository.PopulationDistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PopulationDistService {

    @Autowired
    PopulationDistRepository populationDistRepository;

    public PopulationDist getPopulationDist(String id, long year) {
        Optional<PopulationDist> populationDistResponse = populationDistRepository.findByCountryIdAndYear(id, year);
        return populationDistResponse.orElse(null);
    }

    public PopulationDist addPopulationDist(PopulationDist populationDist) {
        return populationDistRepository.save(populationDist);
    }

    public PopulationDist updatePopulationDist(PopulationDist populationDist) {
        PopulationDist findPopulationDist = getPopulationDist(populationDist.getCountryId(), populationDist.getYear());
        findPopulationDist.setAge(populationDist.getAge());
        findPopulationDist.setPopulation(populationDist.getPopulation());
        return populationDistRepository.save(findPopulationDist);
    }

    public void deletePopulationDist(String id, long year) {
        PopulationDist populationDist = getPopulationDist(id, year);
        populationDistRepository.delete(populationDist);
    }
}