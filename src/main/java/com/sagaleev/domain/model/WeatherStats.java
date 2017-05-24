package com.sagaleev.domain.model;

import javax.persistence.*;
import java.time.Month;
import java.time.YearMonth;

@Entity
public class WeatherStats {

    @Id
    @GeneratedValue
    private long id;
    private YearMonth yearMonth;

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    private double avgTemperature;
    private double atmospherePressure;
    private double humidity;
    private double windSpeed;
    private double cloudiness;
    private double minTemperature;
    private double maxTemperature;
    private double downfall;

    public WeatherStats() {
    }

    public WeatherStats(YearMonth yearMonth, Hospital hospital, double avgTemperature, double atmospherePressure, double humidity, double windSpeed, double cloudiness, double minTemperature, double maxTemperature, double downfall) {
        this.yearMonth = yearMonth;
        this.hospital = hospital;
        this.avgTemperature = avgTemperature;
        this.atmospherePressure = atmospherePressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.cloudiness = cloudiness;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.downfall = downfall;
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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public double getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(double avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public double getAtmospherePressure() {
        return atmospherePressure;
    }

    public void setAtmospherePressure(double atmospherePressure) {
        this.atmospherePressure = atmospherePressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getCloudiness() {
        return cloudiness;
    }

    public void setCloudiness(double cloudiness) {
        this.cloudiness = cloudiness;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getDownfall() {
        return downfall;
    }

    public void setDownfall(double downfall) {
        this.downfall = downfall;
    }

    @Override
    public String toString() {
        return "WeatherStats {" +
                "id : " + id +
                ", yearMonth : " + yearMonth +
                ", avgTemperature : " + avgTemperature +
                ", atmospherePressure : " + atmospherePressure +
                ", humidity : " + humidity +
                ", windSpeed : " + windSpeed +
                ", cloudiness : " + cloudiness +
                ", minTemperature : " + minTemperature +
                ", maxTemperature : " + maxTemperature +
                ", downfall : " + downfall + "}";
    }
}
