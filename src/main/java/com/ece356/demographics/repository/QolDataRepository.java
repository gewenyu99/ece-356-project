package com.ece356.demographics.repository;

import com.ece356.demographics.model.QolData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Reference https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
// Method name will translate to SQL query!! (Wow... I'm not sure if I want this feature)

@Repository
public interface QolDataRepository extends CrudRepository<QolData, String> {
    Optional<QolData> findByCountryIdAndYear(String id, long year);
}