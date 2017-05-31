package com.sagaleev.controllers;

import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.service.WeatherStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WeatherController {

    private final WeatherStatsService weatherStatsService;

    @Autowired
    public WeatherController(WeatherStatsService weatherStatsService) {
        this.weatherStatsService = weatherStatsService;
    }

    @GetMapping(value="/weatherData")
    public String getWeatherData(Model model){
        List<WeatherStats> stats = weatherStatsService.getAll();
        model.addAttribute("stats", stats);
        return "weatherDataPage";
    }
}
