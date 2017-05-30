package com.sagaleev.service.impl;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;
import com.sagaleev.domain.repository.AmbulanceCallStatsRepository;
import com.sagaleev.service.AmbulanceCallStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AmbulanceCallStatsServiceImpl implements AmbulanceCallStatsService {

    private final AmbulanceCallStatsRepository ambulanceCallStatsRepository;

    @Autowired
    public AmbulanceCallStatsServiceImpl(AmbulanceCallStatsRepository ambulanceCallStatsRepository) {
        this.ambulanceCallStatsRepository = ambulanceCallStatsRepository;
    }

    @Override
    public List<AmbulanceCallStats> getAll() {
        return ambulanceCallStatsRepository.findAll();
    }

    @Override
    public List<AmbulanceCallStats> getAmbulanceCallStatsByYear(int year) {
        List<AmbulanceCallStats> allStats = ambulanceCallStatsRepository.findAll();
        List<AmbulanceCallStats> statsByYear = new ArrayList<>();

        for (AmbulanceCallStats stats : allStats) {
            if (stats.getYearMonth().getYear() == year) statsByYear.add(stats);
        }

        return statsByYear;
    }

    @Override
    public List<AmbulanceCallStats> getAmbulanceCallStatsByYearAndDisease(int year, Disease disease) {
        List<AmbulanceCallStats> allStats = ambulanceCallStatsRepository.findAll();
        List<AmbulanceCallStats> statsByYearAndDisease = new ArrayList<>();

        for (AmbulanceCallStats stats : allStats) {
            if (stats.getYearMonth().getYear() == year && stats.getDisease().equals(disease)) statsByYearAndDisease.add(stats);
        }

        return statsByYearAndDisease;
    }

    @Override
    public List<AmbulanceCallStats> getAllByDisease(Disease disease) {
        return ambulanceCallStatsRepository.findAllByDisease(disease);
    }

    @Override
    public void saveDiseaseStatistics(AmbulanceCallStats statistics) {
        ambulanceCallStatsRepository.save(statistics);
    }
}
