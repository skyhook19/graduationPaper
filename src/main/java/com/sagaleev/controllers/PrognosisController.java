package com.sagaleev.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrognosisController {

    @GetMapping(value = "/getPrognosis")
    public String getPrognosis() {
        return "getPrognosis";
    }
}
