package com.scaler.userservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ValidateTokenRequestDto {
    private UUID userId;
    private String token;
}
