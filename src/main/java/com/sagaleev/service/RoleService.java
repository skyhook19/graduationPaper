package com.sagaleev.service;


import com.sagaleev.domain.model.Role;

public interface RoleService {
    Role getRoleByAuthority(String authority);
}

