package com.sagaleev.service.auth;


import com.sagaleev.domain.model.Hospital;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails{
    private Hospital hospital;

    public UserDetailsImpl(Hospital hospital) {
        this.hospital = hospital;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return hospital.getRoles();
    }

    @Override
    public String getPassword() {
        return hospital.getPassword();
    }

    @Override
    public String getUsername() {
        return hospital.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
