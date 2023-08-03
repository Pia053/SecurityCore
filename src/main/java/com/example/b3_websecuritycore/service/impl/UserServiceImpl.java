package com.example.b3_websecuritycore.service.impl;

import com.example.b3_websecuritycore.entity.Role;
import com.example.b3_websecuritycore.entity.User;
import com.example.b3_websecuritycore.model.UserDto;
import com.example.b3_websecuritycore.repository.IRoleRepository;
import com.example.b3_websecuritycore.repository.IUserRepository;
import com.example.b3_websecuritycore.service.IUserService;
import com.example.b3_websecuritycore.util.TbContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        Role role = iRoleRepository.findByName(TbContants.Roles.USER);
        if (role == null) {
            role = iRoleRepository.save(Role.builder().name(TbContants.Roles.USER).build());
        }
        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(Arrays.asList(role))
                .build();
        iUserRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return iUserRepository.findByEmail(email);
    }
}
