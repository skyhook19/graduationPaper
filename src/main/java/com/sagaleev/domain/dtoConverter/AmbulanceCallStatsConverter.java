package com.sagaleev.domain.dtoConverter;

import com.sagaleev.domain.dto.AmbulanceCallStatsDto;
import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;

import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AmbulanceCallStatsConverter {

    public static Map<Disease, String> diseaseNaming = new HashMap<Disease, String>() {{
        put(Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, "ОРВИ");
        put(Disease.MYOCARDIAL_INFARCTION, "Инфаркт миокарда");
        put(Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, "Инфаркт миокарда с госпитализацией");
        put(Disease.FATAL_MYOCARDIAL_INFARCTION, "Инфаркт миокарда со смерт. исходом");
        put(Disease.ANGINA_PECTORIS, "Стенокардия");
        put(Disease.ARRHYTHMIA, "Аритмия сердца");
        put(Disease.CARDIO_VASCULAR_DISEASE, "Сердечно-сосудистые заболевания");
        put(Disease.FATAL_CARDIO_VASCULAR_DISEASE, "Сердечно-сосудистые заболевания со смерт. исходом");
        put(Disease.STROKE, "Инсульт");
        put(Disease.FATAL_STROKE, "Инсульт со смерт. исходом");
        put(Disease.PNEUMONIA, "Пневмония");
        put(Disease.BRONCHIAL_ASTHMA, "Бронхиальная астма");
        put(Disease.BRONCHITIS, "Бронхит");
        put(Disease.PEPTIC_ULCER_DISEASE, "Язвенная болезнь");
        put(Disease.GASTRITIS, "Гастрит");

    }};

    public static Map<Month, String> monthNaming = new HashMap<Month, String>() {{
        put(Month.JANUARY, "Январь");
        put(Month.FEBRUARY, "Февраль");
        put(Month.MARCH, "Март");
        put(Month.APRIL, "Апрель");
        put(Month.MAY, "Май");
        put(Month.JUNE, "Июнь");
        put(Month.JULY, "Июль");
        put(Month.AUGUST, "Август");
        put(Month.SEPTEMBER, "Сентябрь");
        put(Month.OCTOBER, "Октябрь");
        put(Month.NOVEMBER, "Ноябрь");
        put(Month.DECEMBER, "Декабрь");
    }};

    public static AmbulanceCallStatsDto convertToDto(AmbulanceCallStats ambulanceCallStats) {
        AmbulanceCallStatsDto statsDto = new AmbulanceCallStatsDto();
        statsDto.setYear(ambulanceCallStats.getYear());
        statsDto.setMonthName(monthNaming.get(ambulanceCallStats.getMonth()));
        statsDto.setDiseaseName(diseaseNaming.get(ambulanceCallStats.getDisease()));
        statsDto.setCount(ambulanceCallStats.getCount());

        return statsDto;
    }

    public static List<AmbulanceCallStatsDto> convertToDto(List<AmbulanceCallStats> ambulanceCallStatsList) {
        List<AmbulanceCallStatsDto> dtoList = new ArrayList<>(ambulanceCallStatsList.size());

        for (AmbulanceCallStats stats :
                ambulanceCallStatsList) {
            dtoList.add(convertToDto(stats));
        }

        return dtoList;
    }
}
