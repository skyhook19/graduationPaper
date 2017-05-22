package com.sagaleev.controllers.rest;

import com.sagaleev.domain.model.WeatherStats;
import com.sagaleev.service.WeatherStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class RestWeatherStatsController {

    private final WeatherStatsService service;

    @Autowired
    public RestWeatherStatsController(WeatherStatsService service) {
        this.service = service;
    }

    @PostMapping("/getWeatherDataForYear")
    public double[] getWeatherDataForYear(@RequestBody Map<String, String> map){
        Integer year = new Integer(map.get("year"));
        String weatherName = map.get("weatherName");

        List<WeatherStats> statsForYear = service.getWeatherStatsByYear(year);
        List<Double> values = new ArrayList<>(statsForYear.size());

        for (WeatherStats stats :
                statsForYear) {
            values.add(getWeatherValueByName(stats, weatherName));
        }

        return values.stream().mapToDouble(d->d).toArray();
    }


    private double getWeatherValueByName(WeatherStats stats, String weatherName){
        switch(weatherName){
            case "Температура":
                return stats.getAvgTemperature();
            case "Атмосферное давление":
                return stats.getAtmospherePressure();
            case "Относительная влажность":
                return stats.getHumidity();
            case "Скорость ветра":
                return stats.getWindSpeed();
            case "Облачность":
                return stats.getCloudiness();
            case "Мин. температура":
                return stats.getMinTemperature();
            case "Макс. температура":
                return stats.getMaxTemperature();
            case "Количество осадков":
                return stats.getDownfall();
            default: return 0;
        }
    }
}
