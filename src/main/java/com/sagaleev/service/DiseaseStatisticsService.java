package com.sagaleev.service;


import com.sagaleev.domain.model.DiseaseStatistics;
import com.sagaleev.domain.model.Hospital;

import java.time.Month;
import java.util.List;

public interface DiseaseStatisticsService {
    DiseaseStatistics getDiseaseStatisticsById(Long id);

    List<DiseaseStatistics> getDiseaseStatisticsByYear(int Year);

    List<DiseaseStatistics> getDiseaseStatisticsByMonth(Month month);

    List<DiseaseStatistics> getDiseaseStatisticsByHospital(Hospital hospital);

    DiseaseStatistics getDiseaseByHospitalAndYear(Hospital hospital, int year);

    DiseaseStatistics getDiseaseHospitalAndYearAndMonth(Hospital hospital, int year, Month month);

    void saveDiseaseStatistics(DiseaseStatistics statistics);
}
