package com.sagaleev.service.impl;


import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.domain.repository.WeatherStatsRepository;
import com.sagaleev.service.WeatherStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherStatsServiceImpl implements WeatherStatsService{

    private final WeatherStatsRepository repository;

    @Autowired
    public WeatherStatsServiceImpl(WeatherStatsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<WeatherStats> getByYear(int year) {
        List<WeatherStats> allStats = repository.findAll();
        List<WeatherStats> statsByYear = new ArrayList<>();

        for (WeatherStats stats : allStats) {
            if(stats.getYearMonth().getYear() == year) statsByYear.add(stats);
        };

        return statsByYear;
    }

    @Override
    public List<WeatherStats> getAll() {
        return repository.findAll();
    }

    @Override
    public void save(WeatherStats statistics) {
        repository.save(statistics);
    }
}
