package com.sagaleev.domain.repository;

import com.sagaleev.domain.model.DiseaseStatistics;
import com.sagaleev.domain.model.Hospital;
import org.springframework.data.repository.CrudRepository;

import java.time.Month;
import java.util.List;


public interface DiseaseStatisticsRepository extends CrudRepository<DiseaseStatistics, Long>{

    @Override
    List<DiseaseStatistics> findAll();

    List<DiseaseStatistics> findAllByYear(int year);

    List<DiseaseStatistics> findAllByMonth(Month month);

    List<DiseaseStatistics> findAllByHospital(Hospital hospital);

    DiseaseStatistics findByHospitalAndYear(Hospital hospital, int year);

    DiseaseStatistics findByHospitalAndYearAndMonth(Hospital hospital, int year, Month month);
}
