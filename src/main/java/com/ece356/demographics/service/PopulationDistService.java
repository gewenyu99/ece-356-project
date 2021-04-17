package com.ece356.demographics.service;

import com.ece356.demographics.model.PopulationDist;
import com.ece356.demographics.repository.PopulationDistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PopulationDistService {

    @Autowired
    PopulationDistRepository populationDistRepository;

    public PopulationDist getPopulationDist(String id, long year) {
        Optional<PopulationDist> populationDistResponse = populationDistRepository.findByCountryIdAndYear(id, year);
        return populationDistResponse.orElse(null);
    }

    public List<PopulationDist> getPopulationDistBetween(String id, long startYear, long endYear) {
        return populationDistRepository.findByCountryIdAndYearBetween(id, startYear, endYear);
    }

    public PopulationDist addPopulationDist(PopulationDist populationDist) {
        return populationDistRepository.save(populationDist);
    }

    public PopulationDist updatePopulationDist(PopulationDist populationDist) {
        PopulationDist findPopulationDist = getPopulationDist(populationDist.getCountryId(), populationDist.getYear());
        findPopulationDist.start_0 = populationDist.start_0;
        findPopulationDist.start_5 = populationDist.start_5;
        findPopulationDist.start_10 = populationDist.start_10;
        findPopulationDist.start_15 = populationDist.start_15;
        findPopulationDist.start_20 = populationDist.start_20;
        findPopulationDist.start_25 = populationDist.start_25;
        findPopulationDist.start_30 = populationDist.start_30;
        findPopulationDist.start_35 = populationDist.start_35;
        findPopulationDist.start_40 = populationDist.start_40;
        findPopulationDist.start_45 = populationDist.start_45;
        findPopulationDist.start_50 = populationDist.start_50;
        findPopulationDist.start_55 = populationDist.start_55;
        findPopulationDist.start_60 = populationDist.start_60;
        findPopulationDist.start_65 = populationDist.start_65;
        findPopulationDist.start_70 = populationDist.start_70;
        findPopulationDist.start_75 = populationDist.start_75;
        findPopulationDist.start_80 = populationDist.start_80;
        findPopulationDist.start_85 = populationDist.start_85;
        findPopulationDist.start_90 = populationDist.start_90;
        findPopulationDist.start_95 = populationDist.start_95;
        findPopulationDist.start_100 = populationDist.start_100;
        return populationDistRepository.save(findPopulationDist);
    }

    public void deletePopulationDist(String id, long year) {
        PopulationDist populationDist = getPopulationDist(id, year);
        populationDistRepository.delete(populationDist);
    }
}