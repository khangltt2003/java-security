package com.dylan.spring_security_jwt.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDTO {
    private int statusCode;
    private String message;
    private Object data;
}
