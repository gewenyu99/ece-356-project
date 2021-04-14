package com.ece356.demographics.repository;

import com.ece356.demographics.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends CrudRepository<Country, String> {
    Optional<Country> findById(String id);
}