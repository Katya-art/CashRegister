package com.epam.KaterynaKravchenko.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MERCHANDISER, CASHIER, SENIOR_CASHIER;

    @Override
    public String getAuthority() {
        return name();
    }
}
