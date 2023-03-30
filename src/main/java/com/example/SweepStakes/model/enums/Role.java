package com.example.SweepStakes.model.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_USER("User"), ROLE_ADMIN("Admin");
    private final String role;
    public String getAuthority() {
        return name();
    }
}
