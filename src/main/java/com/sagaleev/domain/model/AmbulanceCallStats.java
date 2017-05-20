package com.sagaleev.domain.model;

import javax.persistence.*;
import java.time.Month;

@Entity
public class AmbulanceCallStats {

    @Id
    @GeneratedValue
    private long id;
    private int year;
    private Month month;
    private Disease disease;
    private int count;

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    public AmbulanceCallStats() {
    }

    public AmbulanceCallStats(int year, Month month, Disease disease, int count, Hospital hospital) {
        this.year = year;
        this.month = month;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "AmbulanceCallStats { id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", hospitalId=" + hospital.getId() +
                ", disease=" + disease +
                ", count=" + count;
    }
}
