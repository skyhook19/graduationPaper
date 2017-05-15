package com.sagaleev.controllers;

import com.sagaleev.service.DiseaseStatisticsService;
import com.sagaleev.service.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AmbulanceCallStatsController {
    private final HospitalService hospitalService;
    private final DiseaseStatisticsService diseaseStatisticsService;

    public AmbulanceCallStatsController(HospitalService hospitalService, DiseaseStatisticsService diseaseStatisticsService) {
        this.hospitalService = hospitalService;
        this.diseaseStatisticsService = diseaseStatisticsService;
    }

    @RequestMapping(value = "/weatherDiseaseChart", method = RequestMethod.GET)
    public String getWeatherDiseaseStats(Model model){
        return "weatherDiseaseChart";
    }

    @GetMapping(value = "/yearDiseasesPiechart")
    public String getYearDiseasesStats(){
        return "yearDiseasesPiechart";
    }
}
