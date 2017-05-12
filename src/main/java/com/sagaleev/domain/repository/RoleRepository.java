package com.sagaleev.domain.repository;

import com.sagaleev.domain.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Override
    List<Role> findAll();

    Role findByAuthority(String authority);
}