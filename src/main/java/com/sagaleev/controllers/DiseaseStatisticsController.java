package com.sagaleev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiseaseStatisticsController {

    @GetMapping(value = "/weatherDiseaseChart")
    public String getweatherDiseaseStats(){
        return "weatherDiseaseChart";
    }

    @GetMapping(value = "/yearDiseasesPiechart")
    public String getYearDiseasesStats(){
        return "yearDiseasesPiechart";
    }



}
