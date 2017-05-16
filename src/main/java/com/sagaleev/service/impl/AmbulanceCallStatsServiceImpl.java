package com.sagaleev.service.impl;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;
import com.sagaleev.domain.model.Hospital;
import com.sagaleev.domain.repository.AmbulanceCallStatsRepository;
import com.sagaleev.domain.repository.HospitalRepository;
import com.sagaleev.service.AmbulanceCallStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;

@Service
public class AmbulanceCallStatsServiceImpl implements AmbulanceCallStatsService {

    private final AmbulanceCallStatsRepository ambulanceCallStatsRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public AmbulanceCallStatsServiceImpl(AmbulanceCallStatsRepository ambulanceCallStatsRepository, HospitalRepository hospitalRepository) {
        this.ambulanceCallStatsRepository = ambulanceCallStatsRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public List<AmbulanceCallStats> getAmbulanceCallStatsByYear(int year) {
        return ambulanceCallStatsRepository.findAllByYear(year);
    }

    @Override
    public List<AmbulanceCallStats> getAmbulanceCallStatsByYearAndDisease(int year, Disease disease) {
        return ambulanceCallStatsRepository.findAllByYearAndDisease(year, disease);
    }

    @Override
    public void saveDiseaseStatistics(AmbulanceCallStats statistics) {
        ambulanceCallStatsRepository.save(statistics);
    }
}
