package com.sagaleev.domain.model;

import javax.persistence.*;
import java.time.Month;

@Entity
public class DiseaseStatistics {

    @Id
    @GeneratedValue
    private long id;
    private int year;
    private Month month;

    private int acuteRespiratoryViralInfection; //орви
    private int myocardialInfarction; //инфаркт миокарда
    private int myocardialInfarctionWithHospitalization;
    private int fatalMyocardialInfarction;
    private int anginaPectoris; // стенокардия
    private int arrhythmia; // аритмия сердца
    private int cardioVascularDisease; //сердечно-сосудистые заболевания
    private int fatalCardioVascularDisease;
    private int stroke; //инсульт
    private int fatalStroke;
    private int pneumonia;
    private int bronchialAsthma; //бронхиальная астма
    private int bronchitis; //бронхит
    private int pepticUlcerDisease; // язвенная болезнь
    private int gastritis; // гастрит

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    public DiseaseStatistics() {
    }

    public DiseaseStatistics(int year, Month month, int acuteRespiratoryViralInfection, int myocardialInfarction,
                             int myocardialInfarctionWithHospitalization, int fatalMyocardialInfarction,
                             int anginaPectoris, int arrhythmia, int cardioVascularDisease, int fatalCardioVascularDisease,
                             int stroke, int fatalStroke, int pneumonia, int bronchialAsthma, int bronchitis,
                             int pepticUlcerDisease, int gastritis, Hospital hospital) {
        this.year = year;
        this.month = month;
        this.acuteRespiratoryViralInfection = acuteRespiratoryViralInfection;
        this.myocardialInfarction = myocardialInfarction;
        this.myocardialInfarctionWithHospitalization = myocardialInfarctionWithHospitalization;
        this.fatalMyocardialInfarction = fatalMyocardialInfarction;
        this.anginaPectoris = anginaPectoris;
        this.arrhythmia = arrhythmia;
        this.cardioVascularDisease = cardioVascularDisease;
        this.fatalCardioVascularDisease = fatalCardioVascularDisease;
        this.stroke = stroke;
        this.fatalStroke = fatalStroke;
        this.pneumonia = pneumonia;
        this.bronchialAsthma = bronchialAsthma;
        this.bronchitis = bronchitis;
        this.pepticUlcerDisease = pepticUlcerDisease;
        this.gastritis = gastritis;
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

    public int getAcuteRespiratoryViralInfection() {
        return acuteRespiratoryViralInfection;
    }

    public void setAcuteRespiratoryViralInfection(int acuteRespiratoryViralInfection) {
        this.acuteRespiratoryViralInfection = acuteRespiratoryViralInfection;
    }

    public int getMyocardialInfarction() {
        return myocardialInfarction;
    }

    public void setMyocardialInfarction(int myocardialInfarction) {
        this.myocardialInfarction = myocardialInfarction;
    }

    public int getMyocardialInfarctionWithHospitalization() {
        return myocardialInfarctionWithHospitalization;
    }

    public void setMyocardialInfarctionWithHospitalization(int myocardialInfarctionWithHospitalization) {
        this.myocardialInfarctionWithHospitalization = myocardialInfarctionWithHospitalization;
    }

    public int getFatalMyocardialInfarction() {
        return fatalMyocardialInfarction;
    }

    public void setFatalMyocardialInfarction(int fatalMyocardialInfarction) {
        this.fatalMyocardialInfarction = fatalMyocardialInfarction;
    }

    public int getAnginaPectoris() {
        return anginaPectoris;
    }

    public void setAnginaPectoris(int anginaPectoris) {
        this.anginaPectoris = anginaPectoris;
    }

    public int getArrhythmia() {
        return arrhythmia;
    }

    public void setArrhythmia(int arrhythmia) {
        this.arrhythmia = arrhythmia;
    }

    public int getCardioVascularDisease() {
        return cardioVascularDisease;
    }

    public void setCardioVascularDisease(int cardioVascularDisease) {
        this.cardioVascularDisease = cardioVascularDisease;
    }

    public int getFatalCardioVascularDisease() {
        return fatalCardioVascularDisease;
    }

    public void setFatalCardioVascularDisease(int fatalCardioVascularDisease) {
        this.fatalCardioVascularDisease = fatalCardioVascularDisease;
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }

    public int getFatalStroke() {
        return fatalStroke;
    }

    public void setFatalStroke(int fatalStroke) {
        this.fatalStroke = fatalStroke;
    }

    public int getPneumonia() {
        return pneumonia;
    }

    public void setPneumonia(int pneumonia) {
        this.pneumonia = pneumonia;
    }

    public int getBronchialAsthma() {
        return bronchialAsthma;
    }

    public void setBronchialAsthma(int bronchialAsthma) {
        this.bronchialAsthma = bronchialAsthma;
    }

    public int getBronchitis() {
        return bronchitis;
    }

    public void setBronchitis(int bronchitis) {
        this.bronchitis = bronchitis;
    }

    public int getPepticUlcerDisease() {
        return pepticUlcerDisease;
    }

    public void setPepticUlcerDisease(int pepticUlcerDisease) {
        this.pepticUlcerDisease = pepticUlcerDisease;
    }

    public int getGastritis() {
        return gastritis;
    }

    public void setGastritis(int gastritis) {
        this.gastritis = gastritis;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", acuteRespiratoryViralInfection =" + acuteRespiratoryViralInfection +
                ", myocardialInfarction=" + myocardialInfarction +
                ", myocardialInfarctionWithHospitalization=" + myocardialInfarctionWithHospitalization +
                ", fatalMyocardialInfarction=" + fatalMyocardialInfarction +
                ", anginaPectoris=" + anginaPectoris +
                ", arrhythmia=" + arrhythmia +
                ", cardioVascularDisease=" + cardioVascularDisease +
                ", fatalCardioVascularDisease=" + fatalCardioVascularDisease +
                ", stroke=" + stroke +
                ", fatalStroke=" + fatalStroke +
                ", pneumonia=" + pneumonia +
                ", bronchialAsthma=" + bronchialAsthma +
                ", bronchitis=" + bronchitis +
                ", pepticUlcerDisease=" + pepticUlcerDisease +
                ", gastritis=" + gastritis +
                ", bronchialAsthma=" + bronchialAsthma;
    }
}
