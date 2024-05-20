package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.SetUserRolesRequestDto;
import com.scaler.userservice.dtos.UserDto;
import com.scaler.userservice.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") Long id) {
        UserDto userDto = userService.getUserDetails(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDto> setUserRoles(@PathVariable("id") UUID id, @RequestBody SetUserRolesRequestDto setUserRolesRequestDto) {
        UserDto userDto = userService.setUserRoles(id, setUserRolesRequestDto.getRoleIds());
        return ResponseEntity.ok(userDto);
    }

}
