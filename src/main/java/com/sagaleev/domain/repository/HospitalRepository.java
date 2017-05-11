package com.sagaleev.domain.repository;

import com.sagaleev.domain.model.Hospital;
import org.springframework.data.repository.CrudRepository;

public interface HospitalRepository extends CrudRepository<Hospital, Long>{
    Hospital findOneByName(String name);

    Hospital findByLogin(String login);
}
