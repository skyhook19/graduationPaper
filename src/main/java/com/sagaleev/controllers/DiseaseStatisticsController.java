package com.sagaleev.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiseaseStatisticsController {

    @GetMapping(value = "/stats")
    public String getAll(){
        return "index";
    }
}
