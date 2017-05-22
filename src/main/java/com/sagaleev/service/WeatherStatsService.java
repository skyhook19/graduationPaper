package com.sagaleev.service;

import com.sagaleev.domain.model.WeatherStats;

import java.util.List;


public interface WeatherStatsService {

    List<WeatherStats> getWeatherStatsByYear(int year);

    void saveWeatherStats(WeatherStats statistics);
}
