package com.dylan.spring_security_jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
public class LoginResponseDTO {
    private String jwtToken;
    private long expiration;
}
