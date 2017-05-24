package com.sagaleev.domain.dto;

import java.time.YearMonth;

public class AmbulanceCallStatsDto {
    private YearMonth yearMonth;
    private String diseaseName;
    private int count;

    public AmbulanceCallStatsDto() {
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
