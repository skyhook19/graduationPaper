package com.sagaleev.service.auth;

import com.sagaleev.domain.model.Hospital;
import com.sagaleev.domain.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final HospitalRepository hospitalRepository;

    @Autowired
    public UserDetailsServiceImpl(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Hospital hospital = hospitalRepository.findByLogin(login);
        if (hospital == null) {
            throw new UsernameNotFoundException("Hospital Not Found");
        }
        return new UserDetailsImpl(hospital);
    }
}

