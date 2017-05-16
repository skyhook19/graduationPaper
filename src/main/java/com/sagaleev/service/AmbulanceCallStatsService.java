package com.sagaleev.service;


import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;
import com.sagaleev.domain.model.Hospital;

import java.time.Month;
import java.util.List;

public interface AmbulanceCallStatsService {

    List<AmbulanceCallStats> getAmbulanceCallStatsByYear(int Year);

    List<AmbulanceCallStats> getAmbulanceCallStatsByYearAndDisease(int year, Disease disease);

    void saveDiseaseStatistics(AmbulanceCallStats statistics);
}
