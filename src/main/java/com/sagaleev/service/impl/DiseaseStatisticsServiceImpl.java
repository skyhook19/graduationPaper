package com.sagaleev.service.impl;

import com.sagaleev.domain.model.DiseaseStatistics;
import com.sagaleev.domain.model.Hospital;
import com.sagaleev.domain.repository.DiseaseStatisticsRepository;
import com.sagaleev.domain.repository.HospitalRepository;
import com.sagaleev.service.DiseaseStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;

@Service
public class DiseaseStatisticsServiceImpl implements DiseaseStatisticsService{

    private final DiseaseStatisticsRepository diseaseStatisticsRepository;
    private final HospitalRepository hospitalRepository;

    @Autowired
    public DiseaseStatisticsServiceImpl(DiseaseStatisticsRepository diseaseStatisticsRepository, HospitalRepository hospitalRepository) {
        this.diseaseStatisticsRepository = diseaseStatisticsRepository;
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public DiseaseStatistics getDiseaseStatisticsById(Long id) {
        return diseaseStatisticsRepository.findOne(id);
    }

    @Override
    public List<DiseaseStatistics> getDiseaseStatisticsByYear(int year) {
        return diseaseStatisticsRepository.findAllByYear(year);
    }

    @Override
    public List<DiseaseStatistics> getDiseaseStatisticsByMonth(Month month) {
        return diseaseStatisticsRepository.findAllByMonth(month);
    }

    @Override
    public List<DiseaseStatistics> getDiseaseStatisticsByHospital(Hospital hospital) {
        return diseaseStatisticsRepository.findAllByHospital(hospital);
    }

    @Override
    public DiseaseStatistics getDiseaseByHospitalAndYear(Hospital hospital, int year) {
        return diseaseStatisticsRepository.findByHospitalAndYear(hospital, year);
    }

    @Override
    public DiseaseStatistics getDiseaseHospitalAndYearAndMonth(Hospital hospital, int year, Month month) {
        return diseaseStatisticsRepository.findByHospitalAndYearAndMonth(hospital, year, month);
    }


    @Override
    public void saveDiseaseStatistics(DiseaseStatistics statistics) {
        diseaseStatisticsRepository.save(statistics);
    }
}
