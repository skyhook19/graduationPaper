package com.sagaleev.service.impl;

import com.sagaleev.domain.model.Role;
import com.sagaleev.domain.repository.RoleRepository;
import com.sagaleev.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByAuthority(String authority) {
        return roleRepository.findByAuthority(authority);
    }
}
