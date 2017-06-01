package com.sagaleev.domain.dtoConverter;

import com.sagaleev.domain.dto.AmbulanceCallStatsDto;
import com.sagaleev.domain.model.AmbulanceCallStats;
import com.sagaleev.domain.model.Disease;
import com.sagaleev.domain.model.Hospital;

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

    public static AmbulanceCallStatsDto convertListForOneMonthToOneDto(List<AmbulanceCallStats> ambulanceCallStats) {
        if (ambulanceCallStats.size() != 15) {
            System.out.println("wrong list input");
            return null;
        }

        AmbulanceCallStatsDto statsDto = new AmbulanceCallStatsDto();
        int[] statsForMonth = new int[ambulanceCallStats.size()];
        for (int i = 0; i < ambulanceCallStats.size(); i++) {
            statsForMonth[i] = ambulanceCallStats.get(i).getCount();
        }
        statsDto.setAllDiseasesStats(statsForMonth);
        statsDto.setYearMonth(ambulanceCallStats.get(0).getYearMonth());
        return statsDto;
    }

    public static List<AmbulanceCallStats> convertOneDtoToList(AmbulanceCallStatsDto dto, Hospital hospital){
        List<AmbulanceCallStats> stats = new ArrayList<>(15);

        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.ACUTE_RESPIRATORY_VIRAL_INFECTION, dto.getAcute_respiratory_viral_infection(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.MYOCARDIAL_INFARCTION, dto.getMyocardial_infarction(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.MYOCARDIAL_INFARCTION_WITH_HOSPITALIZATION, dto.getMyocardial_infarction_with_hospitalization(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.FATAL_MYOCARDIAL_INFARCTION, dto.getFatal_myocardial_infarction(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.ANGINA_PECTORIS, dto.getAngina_pectoris(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.ARRHYTHMIA, dto.getArrhythmia(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.CARDIO_VASCULAR_DISEASE, dto.getCardio_vascular_disease(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.FATAL_CARDIO_VASCULAR_DISEASE, dto.getFatal_cardio_vascular_disease(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.STROKE, dto.getStroke(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.FATAL_STROKE, dto.getFatal_stroke(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.PNEUMONIA, dto.getPneumonia(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.BRONCHIAL_ASTHMA, dto.getBronchial_asthma(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.BRONCHITIS, dto.getBronchitis(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.PEPTIC_ULCER_DISEASE, dto.getPeptic_ulcer_disease(), hospital));
        stats.add(new AmbulanceCallStats(dto.getYearMonth(), Disease.GASTRITIS, dto.getGastritis(), hospital));

        return stats;
    }
}
