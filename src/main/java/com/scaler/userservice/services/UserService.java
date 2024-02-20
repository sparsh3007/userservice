package com.scaler.userservice.services;

import com.scaler.userservice.dtos.UserDto;
import com.scaler.userservice.models.Role;
import com.scaler.userservice.models.User;
import com.scaler.userservice.repositeries.RoleRepository;
import com.scaler.userservice.repositeries.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleService roleService;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleService roleService,
                       RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }
    public UserDto getUserDetails(Long userId) {
        return new UserDto(); // returning an empty user for now. Update this to fetch user details from the DB
    }
    public UserDto setUserRoles(Long userId, List<Long> roleIds){
        Optional<User> userOptional = userRepository.findById(userId);
        List<Role> roles = roleRepository.findAllById(roleIds);

        if(userOptional.isEmpty()){
            return null;
        }
        User user = userOptional.get();
        user.setRoles(Set.copyOf(roles));
        User savedUser= userRepository.save(user);
        return UserDto.from(savedUser);

    }
}
