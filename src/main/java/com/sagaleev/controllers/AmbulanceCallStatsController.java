package com.sagaleev.controllers;

import com.sagaleev.service.AmbulanceCallStatsService;
import com.sagaleev.service.HospitalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AmbulanceCallStatsController {

   /* @RequestMapping(value = "/weatherDiseaseChart", method = RequestMethod.GET)
    public String getWeatherDiseaseStats(Model model){
        return "weatherDiseaseChart";
    }*/

    @GetMapping(value = "/yearDiseasesPiechart")
    public String getYearDiseasesStats(){
        return "yearDiseasesPiechart";
    }
}
