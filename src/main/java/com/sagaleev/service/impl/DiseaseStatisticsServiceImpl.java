package com.sagaleev.service.impl;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Hospital;
import com.sagaleev.domain.repository.AmbulanceCallStatsRepository;
import com.sagaleev.domain.repository.HospitalRepository;
import com.sagaleev.service.DiseaseStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;

@Service
public class DiseaseStatisticsServiceImpl implements DiseaseStatisticsService{

    private final AmbulanceCallStatsRepository ambulanceCallStatsRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public DiseaseStatisticsServiceImpl(AmbulanceCallStatsRepository ambulanceCallStatsRepository, HospitalRepository hospitalRepository) {
        this.ambulanceCallStatsRepository = ambulanceCallStatsRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public AmbulanceCallStats getDiseaseStatisticsById(Long id) {
        return ambulanceCallStatsRepository.findOne(id);
    }

    @Override
    public List<AmbulanceCallStats> getDiseaseStatisticsByYear(int year) {
        return ambulanceCallStatsRepository.findAllByYear(year);
    }

    @Override
    public List<AmbulanceCallStats> getDiseaseStatisticsByMonth(Month month) {
        return ambulanceCallStatsRepository.findAllByMonth(month);
    }

    @Override
    public List<AmbulanceCallStats> getDiseaseStatisticsByHospital(Hospital hospital) {
        return ambulanceCallStatsRepository.findAllByHospital(hospital);
    }

    @Override
    public List<AmbulanceCallStats> getDiseasesByHospitalAndYear(Hospital hospital, int year) {
        return ambulanceCallStatsRepository.findByHospitalAndYear(hospital, year);
    }

    @Override
    public AmbulanceCallStats getDiseaseHospitalAndYearAndMonth(Hospital hospital, int year, Month month) {
        return ambulanceCallStatsRepository.findByHospitalAndYearAndMonth(hospital, year, month);
    }


    @Override
    public void saveDiseaseStatistics(AmbulanceCallStats statistics) {
        ambulanceCallStatsRepository.save(statistics);
    }
}
