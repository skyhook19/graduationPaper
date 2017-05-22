package com.sagaleev.domain.model;

import javax.persistence.*;
import java.time.Month;

@Entity
public class WeatherStats {

    @Id
    @GeneratedValue
    private long id;
    private int year;
    private Month month;

    //weather stats
    private double avgTemperature;
    private double atmospherePressure;
    private double humidity; //влажность
    private double windSpeed;
    private double cloudiness; //облачность
    private double minTemperature;
    private double maxTemperature;
    private double downfall; //осадки

    @ManyToOne
    @JoinColumn(name = "hospitalId")
    private Hospital hospital;

    public WeatherStats() {
    }

    public WeatherStats(int year, Month month, double avgTemperature, double atmospherePressure, double humidity, double windSpeed, double cloudiness, double minTemperature, double maxTemperature, double downfall, Hospital hospital) {
        this.year = year;
        this.month = month;
        this.avgTemperature = avgTemperature;
        this.atmospherePressure = atmospherePressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.cloudiness = cloudiness;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
        this.downfall = downfall;
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

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        return "WeatherStats {" +
                "id : " + id +
                ", year : " + year +
                ", month : " + month +
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
