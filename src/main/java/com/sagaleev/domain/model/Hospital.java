package com.sagaleev.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Hospital {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String login;
    private String password;
    private String passwordConfirm;
    private String email;
    private String name;
    private String address;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hospital")
    private List<DiseaseStatistics> diseaseStatistics;

    public Hospital(String login, String password, String passwordConfirm, String email, String name, String address) {
        this.login = login;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.email = email;
        this.name = name;
        this.address = address;
        diseaseStatistics = new ArrayList<>();
    }

    public Hospital(){
        diseaseStatistics = new ArrayList<>();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<DiseaseStatistics> getDiseaseStatistics() {
        return diseaseStatistics;
    }

    public void setDiseaseStatistics(List<DiseaseStatistics> diseaseStatistics) {
        this.diseaseStatistics = diseaseStatistics;
    }

    @Override
    public String toString() {
        return "Hospital, id=" + id +
                ", login=" + login +
                ", email=" + email +
                ", address=" + address +
                ", name=" + name;
    }
}
