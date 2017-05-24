package com.sagaleev.domain.model;

import javax.persistence.*;
import java.time.Month;
import java.time.YearMonth;

@Entity
public class AmbulanceCallStats {

    @Id
    @GeneratedValue
    private long id;
    private YearMonth yearMonth;
    private Disease disease;
    private int count;

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    public AmbulanceCallStats() {
    }

    public AmbulanceCallStats(YearMonth yearMonth, Disease disease, int count, Hospital hospital) {
        this.yearMonth = yearMonth;
        this.disease = disease;
        this.count = count;
        this.hospital = hospital;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "AmbulanceCallStats { id=" + id +
                ", yearMonth=" + yearMonth +
                ", hospitalId=" + hospital.getId() +
                ", disease=" + disease +
                ", count=" + count;
    }
}
