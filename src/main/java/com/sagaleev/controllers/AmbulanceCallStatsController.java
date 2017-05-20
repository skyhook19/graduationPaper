package com.sagaleev.controllers;

import com.sagaleev.domain.dtoConverter.AmbulanceCallStatsConverter;
import com.sagaleev.domain.model.Disease;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class AmbulanceCallStatsController {

    @GetMapping(value = "/weatherDiseaseChart")
    public String getWeatherDiseaseStats(Model model){
        return "weatherDiseaseChart";
    }

    @GetMapping(value = "/yearDiseasesPiechart")
    public String getYearDiseasesStats(){
        return "yearDiseasesPiechart";
    }
}
