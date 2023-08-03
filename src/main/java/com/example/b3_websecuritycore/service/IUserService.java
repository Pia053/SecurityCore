package com.example.b3_websecuritycore.service;

import com.example.b3_websecuritycore.entity.User;
import com.example.b3_websecuritycore.model.UserDto;

public interface IUserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}
