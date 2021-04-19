package com.ece356.demographics.service;

import com.ece356.demographics.model.PopulationDist;
import com.ece356.demographics.model.QolData;
import com.ece356.demographics.repository.QolDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QolDataService {

    @Autowired
    QolDataRepository qolDataRepository;

    public QolData getQolData(String id, long year) {
        Optional<QolData> qolDataResponse = qolDataRepository.findByCountryIdAndYear(id, year);
        return qolDataResponse.orElse(null);
    }

    public List<QolData> getQolDataBetween(String id, long startYear, long endYear) {
        return qolDataRepository.findByCountryIdAndYearBetween(id, startYear, endYear);
    }

    public QolData addQolData(QolData QolData) {
        return qolDataRepository.save(QolData);
    }

    public QolData updateQolData(QolData qolData) {
        QolData findQolData = getQolData(qolData.getCountryId(), qolData.getYear());
        findQolData.setHdi(qolData.getHdi());
        findQolData.setLifeExpectancy(qolData.getLifeExpectancy());
        findQolData.setSurvivalToAge65(qolData.getSurvivalToAge65());
        return qolDataRepository.save(findQolData);
    }

    public void deleteQolData(String id, long year) {
        QolData qolData = getQolData(id, year);
        qolDataRepository.delete(id, year);
    }
}