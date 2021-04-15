package com.ece356.demographics.repository;

import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.model.PopulationDist5YearRange;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PopulationDataRepository extends CrudRepository<PopulationData, String> {
    Optional<PopulationData> findByCountryIdAndYear(String id, long year);
    List<PopulationData> findByCountryIdAndYearBetween(String id, long startYear, long endYear);
}
