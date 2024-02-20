package com.scaler.userservice.dtos;

import com.scaler.userservice.models.Role;
import com.scaler.userservice.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDto {
    private String email;

    private Set<Role> roles=new HashSet<>();

    // create a deep copy from existing user
    public static UserDto from(User user) {
        UserDto userDto = new UserDto();

        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());
        return userDto;
    }
}
