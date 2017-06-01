package com.sagaleev.controllers;

import com.sagaleev.domain.dto.AmbulanceCallStatsDto;
import com.sagaleev.domain.dtoConverter.AmbulanceCallStatsConverter;
import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Hospital;
import com.sagaleev.service.AmbulanceCallStatsService;
import com.sagaleev.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Controller
public class AmbulanceCallStatsController {

    private final AmbulanceCallStatsService ambulanceCallStatsService;
    private final HospitalService hospitalService;

    @Autowired
    public AmbulanceCallStatsController(AmbulanceCallStatsService ambulanceCallStatsService, HospitalService hospitalService) {
        this.ambulanceCallStatsService = ambulanceCallStatsService;
        this.hospitalService = hospitalService;
    }

    @GetMapping(value = "/weatherDiseaseChart")
    public String getWeatherDiseaseStats() {
        return "weatherDiseaseChart";
    }

    @GetMapping(value = "/yearDiseasesPiechart")
    public String getYearDiseasesStats() {
        return "yearDiseasesPiechart";
    }

    @GetMapping(value = "/ambulanceCallsData")
    public String getAmbulanceCallsData(Model model) {
        List<AmbulanceCallStats> stats = ambulanceCallStatsService.getAll();
        List<AmbulanceCallStatsDto> dtos = new ArrayList<>(stats.size() / 15);

        Comparator<AmbulanceCallStats> comparator = Comparator.comparing(AmbulanceCallStats::getYearMonth);
        comparator.thenComparing(Comparator.comparing(AmbulanceCallStats::getDisease));
        stats.sort(comparator);

        for (int i = 0, k = 0; k < stats.size() / 15; k++) {
            List<AmbulanceCallStats> statsToConvert = new ArrayList<>(15);
            for (int j = 0; j < 15; i++, j++) {
                statsToConvert.add(stats.get(i));
            }
            dtos.add(AmbulanceCallStatsConverter.convertListForOneMonthToOneDto(statsToConvert));
        }

        model.addAttribute("stats", dtos);
        return "ambulanceCallsDataPage";
    }

    @GetMapping(value = "/addNewAmbulanceCallData")
    public String addNewData(@RequestParam(value = "success", required = false) String success, Model model){
        model.addAttribute("ambStats", new AmbulanceCallStatsDto());
        model.addAttribute("success", success != null);
        return "addNewAmbulanceCallsData";
    }

    @PostMapping(value = "/addNewAmbulanceCallData")
    public String addNewData(@ModelAttribute("ambStats") AmbulanceCallStatsDto ambStats, Model model){
        Hospital hospital = hospitalService.getCurrentHospital();
        List<AmbulanceCallStats> stats = AmbulanceCallStatsConverter.convertOneDtoToList(ambStats, hospital);
        ambulanceCallStatsService.saveAll(stats);

        return "redirect:/addNewAmbulanceCallData?success";
    }
}
