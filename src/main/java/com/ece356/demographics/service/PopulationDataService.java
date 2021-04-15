package com.ece356.demographics.service;

import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.repository.PopulationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PopulationDataService {

    @Autowired
    PopulationDataRepository populationDataRepository;

    public PopulationData getPopulationData(String id, long year) {
        Optional<PopulationData> populationDataResponse = populationDataRepository.findByCountryIdAndYear(id, year);
        return populationDataResponse.orElse(null);
    }

    public PopulationData addPopulationData(PopulationData populationData) {
        return populationDataRepository.save(populationData);
    }

    public PopulationData updatePopulationData(PopulationData populationData) {
        PopulationData findPopulationData = getPopulationData(populationData.getCountryId(), populationData.getYear());
        findPopulationData.setBirthRate(populationData.getBirthRate());
        findPopulationData.setDeathRate(populationData.getDeathRate());
        findPopulationData.setInfantMortalityRate(populationData.getInfantMortalityRate());
        findPopulationData.setTotalPopulation(populationData.getTotalPopulation());
        return populationDataRepository.save(findPopulationData);
    }

    public void deletePopulationData(String id, long year) {
        PopulationData populationData = getPopulationData(id, year);
        populationDataRepository.delete(populationData);
    }
}
