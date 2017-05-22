package com.sagaleev.controllers.rest;

import com.sagaleev.domain.dto.AmbulanceCallStatsDto;
import com.sagaleev.domain.dtoConverter.AmbulanceCallStatsConverter;
import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;
import com.sagaleev.service.AmbulanceCallStatsService;
import org.codehaus.groovy.runtime.powerassert.SourceText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RestAmbulanceCallStatsController {

    private final AmbulanceCallStatsService ambulanceCallStatsService;

    @Autowired
    public RestAmbulanceCallStatsController(AmbulanceCallStatsService ambulanceCallStatsService) {
        this.ambulanceCallStatsService = ambulanceCallStatsService;
    }

    @PostMapping("/getAmbulanceCallStatsByYearAndDisease")
    public int[] getAmbulanceCallStatsForYear(@RequestBody Map<String, String>  map){
        Integer year = new Integer(map.get("year"));
        String diseaseName = map.get("diseaseName");

        Disease disease = null;

        for(Map.Entry<Disease, String> entry: AmbulanceCallStatsConverter.diseaseNaming.entrySet()){
            if(diseaseName.equals(entry.getValue())){
                disease = entry.getKey();
                break;
            }
        }

        List<AmbulanceCallStats> ambulanceCallStats = ambulanceCallStatsService.getAmbulanceCallStatsByYearAndDisease(year, disease);
        List<Integer> ambulanceCallStatsCount = new ArrayList<>(ambulanceCallStats.size());

        for (AmbulanceCallStats stats : ambulanceCallStats) {
            ambulanceCallStatsCount.add(stats.getCount());
        }

        return ambulanceCallStatsCount.stream().mapToInt(i->i).toArray();
    }

    @PostMapping("/getAmbulanceCallStatsByYear")
    public Map<String, Integer> getAmbulanceCallStatsByYear(@RequestBody Map<String, String>  map){
        int year = new Integer(map.get("year"));
        List<AmbulanceCallStats> statsForYear = ambulanceCallStatsService.getAmbulanceCallStatsByYear(year);
        Map<String, Integer> ambulanceCallCountByDisease = new HashMap<>();

        for (AmbulanceCallStats stats:
             statsForYear) {
            Disease disease = stats.getDisease();
            String diseaseName = AmbulanceCallStatsConverter.diseaseNaming.get(disease);

            if (ambulanceCallCountByDisease.containsKey(diseaseName)) {
                int oldCount = ambulanceCallCountByDisease.get(diseaseName);
                ambulanceCallCountByDisease.replace(diseaseName, oldCount + stats.getCount());
            } else {
                ambulanceCallCountByDisease.put(diseaseName, stats.getCount());
            }
        }

        return  ambulanceCallCountByDisease;
    }
}
