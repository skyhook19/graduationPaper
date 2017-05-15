package com.sagaleev.service;


import com.sagaleev.domain.model.Hospital;

public interface HospitalService {
    Hospital getHospitalById(Long id);

    Hospital getHospitalByName(String name);

    Hospital getHospitalByLogin(String login);

    Hospital getCurrentHospital();

    void saveHospital(Hospital hospital);

}
