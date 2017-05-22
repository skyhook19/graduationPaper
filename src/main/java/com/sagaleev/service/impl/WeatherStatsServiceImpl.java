package com.sagaleev.service.impl;


import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.domain.repository.WeatherStatsRepository;
import com.sagaleev.service.WeatherStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherStatsServiceImpl implements WeatherStatsService{

    private final WeatherStatsRepository repository;

    @Autowired
    public WeatherStatsServiceImpl(WeatherStatsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WeatherStats> getWeatherStatsByYear(int year) {
        return repository.findAllByYear(year);
    }
    @Override
    public void saveWeatherStats(WeatherStats statistics) {

    }
}
