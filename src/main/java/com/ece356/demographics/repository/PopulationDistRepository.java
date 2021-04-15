package com.ece356.demographics.repository;

import com.ece356.demographics.model.Country;
import com.ece356.demographics.model.PopulationData;
import com.ece356.demographics.model.PopulationDist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Reference https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
// Method name will translate to SQL query!! (Wow... I'm not sure if I want this feature)

@Repository
public interface PopulationDistRepository extends CrudRepository<PopulationDist, String> {
    Optional<PopulationDist> findByCountryIdAndYear(String id, long year);
    List<PopulationDist> findByCountryIdAndYearBetween(String id, long startYear, long endYear);
}