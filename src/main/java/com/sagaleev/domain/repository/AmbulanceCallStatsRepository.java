package com.sagaleev.domain.repository;

import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;
import com.sagaleev.domain.model.Hospital;
import org.springframework.data.repository.CrudRepository;

import java.time.Month;
import java.util.List;


public interface AmbulanceCallStatsRepository extends CrudRepository<AmbulanceCallStats, Long> {

    @Override
    List<AmbulanceCallStats> findAll();

    List<AmbulanceCallStats> findAllByYear(int year);

    List<AmbulanceCallStats> findAllByYearAndDisease(int year, Disease disease);
}
