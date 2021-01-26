package com.epam.KaterynaKravchenko.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MERCHANDISER, CASHIER, SENIOR_CASHIER;

    @Override
    public String getAuthority() {
        return name();
    }
}
