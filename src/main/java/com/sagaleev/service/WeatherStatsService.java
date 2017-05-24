package com.sagaleev.service;

import com.sagaleev.domain.model.WeatherStats;

import java.util.List;


public interface WeatherStatsService {

    List<WeatherStats> getByYear(int year);

    List<WeatherStats> getAll();

    void save(WeatherStats statistics);
}
