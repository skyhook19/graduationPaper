package com.sagaleev.service;


import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Hospital;

import java.time.Month;
import java.util.List;

public interface DiseaseStatisticsService {
    AmbulanceCallStats getDiseaseStatisticsById(Long id);

    List<AmbulanceCallStats> getDiseaseStatisticsByYear(int Year);

    List<AmbulanceCallStats> getDiseaseStatisticsByMonth(Month month);

    List<AmbulanceCallStats> getDiseaseStatisticsByHospital(Hospital hospital);

    List<AmbulanceCallStats> getDiseasesByHospitalAndYear(Hospital hospital, int year);

    AmbulanceCallStats getDiseaseHospitalAndYearAndMonth(Hospital hospital, int year, Month month);

    void saveDiseaseStatistics(AmbulanceCallStats statistics);
}
