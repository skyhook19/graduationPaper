package com.sagaleev.controllers;

import com.sagaleev.domain.dto.AmbulanceCallStatsDto;
import com.sagaleev.domain.dtoConverter.AmbulanceCallStatsConverter;
import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;
import com.sagaleev.service.AmbulanceCallStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
public class RestAmbulanceCallStatsController {

    private final AmbulanceCallStatsService ambulanceCallStatsService;

    @Autowired
    public RestAmbulanceCallStatsController(AmbulanceCallStatsService ambulanceCallStatsService) {
        this.ambulanceCallStatsService = ambulanceCallStatsService;
    }

    @GetMapping("/weatherDiseaseChart")
    public List<AmbulanceCallStatsDto> getAmbulanceCallStatsForYear(String yearStr, String diseaseName){
        Integer year = new Integer(yearStr);
        Disease disease = Disease.CARDIO_VASCULAR_DISEASE;

        for (Map.Entry<Disease, String> entry :
        AmbulanceCallStatsConverter.diseaseNaming.entrySet()){
            if(entry.getValue().equals(diseaseName)){
                disease = entry.getKey();
                break;
            }
        }

        List<AmbulanceCallStats> statsList = ambulanceCallStatsService.getAmbulanceCallStatsByYearAndDisease(year, disease);
        return AmbulanceCallStatsConverter.convertToDto(statsList);
    }
}
