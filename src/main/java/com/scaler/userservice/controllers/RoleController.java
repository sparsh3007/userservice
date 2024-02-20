package com.scaler.userservice.controllers;

import com.scaler.userservice.dtos.CreateRoleRequestDto;
import com.scaler.userservice.models.Role;
import com.scaler.userservice.services.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private RoleService roleService;
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping
    public ResponseEntity<Role> createRole(CreateRoleRequestDto createRoleRequestDto) {
        Role role = roleService.createRole(createRoleRequestDto.getName());
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
