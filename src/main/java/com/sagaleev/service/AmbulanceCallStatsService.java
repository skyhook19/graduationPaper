package com.sagaleev.service;


import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;

import java.util.List;

public interface AmbulanceCallStatsService {

    List<AmbulanceCallStats> getAll();

    List<AmbulanceCallStats> getAmbulanceCallStatsByYear(int Year);

    List<AmbulanceCallStats> getAmbulanceCallStatsByYearAndDisease(int year, Disease disease);

    List<AmbulanceCallStats> getAllByDisease(Disease disease);

    void saveDiseaseStatistics(AmbulanceCallStats statistics);

    void saveAll(List<AmbulanceCallStats> statsList);
}
