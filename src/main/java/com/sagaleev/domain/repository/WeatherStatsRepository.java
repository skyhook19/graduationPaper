package com.sagaleev.domain.repository;

import com.sagaleev.domain.model.WeatherStats;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeatherStatsRepository extends CrudRepository<WeatherStats, Long> {

    @Override
    List<WeatherStats> findAll();
}
