package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.*;
import com.scaler.userservice.exceptions.NotFoundException;
import com.scaler.userservice.exceptions.PasswordMismatchException;
import com.scaler.userservice.exceptions.UserAlreadyExistsException;
import com.scaler.userservice.models.SessionStatus;
import com.scaler.userservice.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) throws NotFoundException, PasswordMismatchException {
       return authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogOutRequestDto logOutRequestDto) {
        authService.logout(logOutRequestDto.getToken(), logOutRequestDto.getUserId());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException {
        UserDto userDto = authService.signup(signUpRequestDto.getEmail(), signUpRequestDto.getPassword());
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<SessionStatus> validateToken(@RequestBody ValidateTokenRequestDto validateTokenRequestDto) {
        SessionStatus sessionStatus = authService.validateToken(validateTokenRequestDto.getToken(), validateTokenRequestDto.getUserId());
        return ResponseEntity.ok(sessionStatus);
    }
}
