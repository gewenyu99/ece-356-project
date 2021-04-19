package com.ece356.demographics.repository;

import com.ece356.demographics.model.PopulationData;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PopulationDataRepository extends CrudRepository<PopulationData, String> {
    Optional<PopulationData> findByCountryIdAndYear(String id, long year);
    List<PopulationData> findByCountryIdAndYearBetween(String id, long startYear, long endYear);

    @Modifying
    @Query("DELETE FROM population_data WHERE country_id=:countryId and year=:year")
    @Transactional
    void delete(@Param("countryId") String id, @Param("year") long year);

}
