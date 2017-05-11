package com.sagaleev.service.impl;

import com.sagaleev.domain.model.Hospital;
import com.sagaleev.domain.repository.HospitalRepository;
import com.sagaleev.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalServiceImpl implements HospitalService{

    private final HospitalRepository hospitalRepository;

    @Autowired
    public HospitalServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findOne(id);
    }

    @Override
    public Hospital getHospitalByName(String name) {
        return hospitalRepository.findOneByName(name);
    }

    @Override
    public Hospital getHospitalByLogin(String login) {
        return hospitalRepository.findByLogin(login);
    }

    @Override
    public void saveHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }
}
