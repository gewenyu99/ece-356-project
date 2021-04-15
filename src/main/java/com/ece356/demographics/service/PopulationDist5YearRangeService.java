package com.ece356.demographics.service;

import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.model.PopulationDist5YearRange;
import com.ece356.demographics.repository.PopulationDist5YearRangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PopulationDist5YearRangeService {

    @Autowired
    PopulationDist5YearRangeRepository populationDist5YearRangeRepository;

    public PopulationDist5YearRange getPopulationDist5YearRange(String id, long year) {
        Optional<PopulationDist5YearRange> populationDist5YearRangeResponse = populationDist5YearRangeRepository.findByCountryIdAndYear(id, year);
        return populationDist5YearRangeResponse.orElse(null);
    }

    public List<PopulationDist5YearRange> getPopulationDist5YearRangeBetween(String id, long startYear, long endYear) {
        return populationDist5YearRangeRepository.findByCountryIdAndYearBetween(id, startYear, endYear);
    }

    public PopulationDist5YearRange addPopulationDist5YearRange(PopulationDist5YearRange populationDist5YearRange) {
        return populationDist5YearRangeRepository.save(populationDist5YearRange);
    }

    public PopulationDist5YearRange updatePopulationDist5YearRange(PopulationDist5YearRange populationDist5YearRange) {
        PopulationDist5YearRange findPopulationDist5YearRange = getPopulationDist5YearRange(populationDist5YearRange.getCountryId(), populationDist5YearRange.getYear());
        findPopulationDist5YearRange.setStartAge(populationDist5YearRange.getStartAge());
        findPopulationDist5YearRange.setPopulation(populationDist5YearRange.getPopulation());
        return populationDist5YearRangeRepository.save(findPopulationDist5YearRange);
    }

    public void deletePopulationDist5YearRange(String id, long year) {
        PopulationDist5YearRange populationDist5YearRange = getPopulationDist5YearRange(id, year);
        populationDist5YearRangeRepository.delete(populationDist5YearRange);
    }
}