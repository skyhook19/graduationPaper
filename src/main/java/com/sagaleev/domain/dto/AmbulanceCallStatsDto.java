package com.sagaleev.domain.dto;

import java.time.YearMonth;

public class AmbulanceCallStatsDto {
    private YearMonth yearMonth;
    private int acute_respiratory_viral_infection;
    private int myocardial_infarction;
    private int myocardial_infarction_with_hospitalization;
    private int fatal_myocardial_infarction;
    private int angina_pectoris;
    private int arrhythmia;
    private int cardio_vascular_disease;
    private int fatal_cardio_vascular_disease;
    private int stroke;
    private int fatal_stroke;
    private int pneumonia;
    private int bronchial_asthma;
    private int bronchitis;
    private int peptic_ulcer_disease;
    private int gastritis;

    public AmbulanceCallStatsDto(YearMonth yearMonth, int acute_respiratory_viral_infection, int myocardial_infarction, int myocardial_infarction_with_hospitalization, int fatal_myocardial_infarction, int angina_pectoris, int arrhythmia, int cardio_vascular_disease, int fatal_cardio_vascular_disease, int stroke, int fatal_stroke, int pneumonia, int bronchial_asthma, int bronchitis, int peptic_ulcer_disease, int gastritis) {
        this.yearMonth = yearMonth;
        this.acute_respiratory_viral_infection = acute_respiratory_viral_infection;
        this.myocardial_infarction = myocardial_infarction;
        this.myocardial_infarction_with_hospitalization = myocardial_infarction_with_hospitalization;
        this.fatal_myocardial_infarction = fatal_myocardial_infarction;
        this.angina_pectoris = angina_pectoris;
        this.arrhythmia = arrhythmia;
        this.cardio_vascular_disease = cardio_vascular_disease;
        this.fatal_cardio_vascular_disease = fatal_cardio_vascular_disease;
        this.stroke = stroke;
        this.fatal_stroke = fatal_stroke;
        this.pneumonia = pneumonia;
        this.bronchial_asthma = bronchial_asthma;
        this.bronchitis = bronchitis;
        this.peptic_ulcer_disease = peptic_ulcer_disease;
        this.gastritis = gastritis;
    }

    public AmbulanceCallStatsDto() {
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    public int getAcute_respiratory_viral_infection() {
        return acute_respiratory_viral_infection;
    }

    public void setAcute_respiratory_viral_infection(int acute_respiratory_viral_infection) {
        this.acute_respiratory_viral_infection = acute_respiratory_viral_infection;
    }

    public int getMyocardial_infarction() {
        return myocardial_infarction;
    }

    public void setMyocardial_infarction(int myocardial_infarction) {
        this.myocardial_infarction = myocardial_infarction;
    }

    public int getMyocardial_infarction_with_hospitalization() {
        return myocardial_infarction_with_hospitalization;
    }

    public void setMyocardial_infarction_with_hospitalization(int myocardial_infarction_with_hospitalization) {
        this.myocardial_infarction_with_hospitalization = myocardial_infarction_with_hospitalization;
    }

    public int getFatal_myocardial_infarction() {
        return fatal_myocardial_infarction;
    }

    public void setFatal_myocardial_infarction(int fatal_myocardial_infarction) {
        this.fatal_myocardial_infarction = fatal_myocardial_infarction;
    }

    public int getAngina_pectoris() {
        return angina_pectoris;
    }

    public void setAngina_pectoris(int angina_pectoris) {
        this.angina_pectoris = angina_pectoris;
    }

    public int getArrhythmia() {
        return arrhythmia;
    }

    public void setArrhythmia(int arrhythmia) {
        this.arrhythmia = arrhythmia;
    }

    public int getCardio_vascular_disease() {
        return cardio_vascular_disease;
    }

    public void setCardio_vascular_disease(int cardio_vascular_disease) {
        this.cardio_vascular_disease = cardio_vascular_disease;
    }

    public int getFatal_cardio_vascular_disease() {
        return fatal_cardio_vascular_disease;
    }

    public void setFatal_cardio_vascular_disease(int fatal_cardio_vascular_disease) {
        this.fatal_cardio_vascular_disease = fatal_cardio_vascular_disease;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public int getFatal_stroke() {
        return fatal_stroke;
    }

    public void setFatal_stroke(int fatal_stroke) {
        this.fatal_stroke = fatal_stroke;
    }

    public int getPneumonia() {
        return pneumonia;
    }

    public void setPneumonia(int pneumonia) {
        this.pneumonia = pneumonia;
    }

    public int getBronchial_asthma() {
        return bronchial_asthma;
    }

    public void setBronchial_asthma(int bronchial_asthma) {
        this.bronchial_asthma = bronchial_asthma;
    }

    public int getBronchitis() {
        return bronchitis;
    }

    public void setBronchitis(int bronchitis) {
        this.bronchitis = bronchitis;
    }

    public int getPeptic_ulcer_disease() {
        return peptic_ulcer_disease;
    }

    public void setPeptic_ulcer_disease(int peptic_ulcer_disease) {
        this.peptic_ulcer_disease = peptic_ulcer_disease;
    }

    public int getGastritis() {
        return gastritis;
    }

    public void setGastritis(int gastritis) {
        this.gastritis = gastritis;
    }

    public void setAllDiseasesStats(int[] allDiseasesStats) {
        acute_respiratory_viral_infection = allDiseasesStats[0];
        myocardial_infarction = allDiseasesStats[1];
        myocardial_infarction_with_hospitalization = allDiseasesStats[2];
        fatal_myocardial_infarction = allDiseasesStats[3];
        angina_pectoris = allDiseasesStats[4];
        arrhythmia = allDiseasesStats[5];
        cardio_vascular_disease = allDiseasesStats[6];
        fatal_cardio_vascular_disease = allDiseasesStats[7];
        stroke = allDiseasesStats[8];
        fatal_stroke = allDiseasesStats[9];
        pneumonia = allDiseasesStats[10];
        bronchial_asthma = allDiseasesStats[11];
        bronchitis = allDiseasesStats[12];
        peptic_ulcer_disease = allDiseasesStats[13];
        gastritis = allDiseasesStats[14];
    }

    @Override
    public String toString() {
        return "AmbulanceCallStatsDto {" +
                "yearMonth = " + yearMonth.getYear() + yearMonth.getMonth() +
                ", acute_respiratory_viral_infection=" + acute_respiratory_viral_infection +
                ", myocardial_infarction=" + myocardial_infarction +
                ", myocardial_infarction_with_hospitalization" + myocardial_infarction_with_hospitalization +
                ", fatal_myocardial_infarction" + fatal_myocardial_infarction +
                ", angina_pectoris" + angina_pectoris +
                ", arrhythmia" + arrhythmia +
                ", cardio_vascular_disease" + cardio_vascular_disease +
                ", fatal_cardio_vascular_disease" + fatal_cardio_vascular_disease +
                ", stroke" + stroke +
                ", fatal_stroke" + fatal_stroke +
                ", pneumonia" + pneumonia +
                ", bronchial_asthma" + bronchial_asthma +
                ", bronchitis" + bronchitis +
                ", peptic_ulcer_disease" + peptic_ulcer_disease +
                ", gastritis" + gastritis + "}";
    }
}
