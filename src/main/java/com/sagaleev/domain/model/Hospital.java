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
    private List<AmbulanceCallStats> diseaseStatistics;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

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

    public List<AmbulanceCallStats> getDiseaseStatistics() {
        return diseaseStatistics;
    }

    public void setDiseaseStatistics(List<AmbulanceCallStats> diseaseStatistics) {
        this.diseaseStatistics = diseaseStatistics;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Hospital: id=" + id +
                ", login=" + login +
                ", email=" + email +
                ", address=" + address +
                ", name=" + name;
    }
}
