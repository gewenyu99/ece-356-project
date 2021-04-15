package com.ece356.demographics.repository;

import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.model.PopulationDist5YearRange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Reference https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
// Method name will translate to SQL query!! (Wow... I'm not sure if I want this feature)

@Repository
public interface PopulationDist5YearRangeRepository extends CrudRepository<PopulationDist5YearRange, String> {
    Optional<PopulationDist5YearRange> findByCountryIdAndYear(String id, long year);
    List<PopulationDist5YearRange> findByCountryIdAndYearBetween(String id, long startYear, long endYear);
}