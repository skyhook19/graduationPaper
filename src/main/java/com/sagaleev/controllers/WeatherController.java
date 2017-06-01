package com.sagaleev.controllers;

import com.sagaleev.domain.model.Hospital;
import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.service.HospitalService;
import com.sagaleev.service.WeatherStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
public class WeatherController {

    private final WeatherStatsService weatherStatsService;
    private final HospitalService hospitalService;

    @Autowired
    public WeatherController(WeatherStatsService weatherStatsService, HospitalService hospitalService) {
        this.weatherStatsService = weatherStatsService;
        this.hospitalService = hospitalService;
    }

    @GetMapping(value="/weatherData")
    public String getWeatherData(Model model){
        List<WeatherStats> stats = weatherStatsService.getAll();
        stats.sort(Comparator.comparing(WeatherStats::getYearMonth));
        model.addAttribute("stats", stats);
        return "weatherDataPage";
    }

    @GetMapping(value = "/addNewWeatherData")
    public String addNewData(@RequestParam(value = "success", required = false) String success, Model model){
        model.addAttribute("weatherStats", new WeatherStats());
        model.addAttribute("success", success != null);
        return "addNewWeatherData";
    }

    @PostMapping(value = "/addNewWeatherData")
    public String addNewData(@ModelAttribute("weatherStats") WeatherStats weatherStats, Model model){
        Hospital hospital = hospitalService.getCurrentHospital();
        weatherStats.setHospital(hospital);
        weatherStatsService.save(weatherStats);
        return "redirect:/addNewWeatherData?success";
    }
}
