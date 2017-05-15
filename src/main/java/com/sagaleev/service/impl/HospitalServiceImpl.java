package com.sagaleev.service.impl;

import com.sagaleev.domain.model.Hospital;
import com.sagaleev.domain.repository.HospitalRepository;
import com.sagaleev.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Security;

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
    public Hospital getCurrentHospital() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            return hospitalRepository.findByLogin(authentication.getName());
        }
        return null;
    }

    @Override
    public void saveHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }
}
