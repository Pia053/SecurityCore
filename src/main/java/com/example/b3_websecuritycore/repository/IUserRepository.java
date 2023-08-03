package com.example.b3_websecuritycore.repository;

import com.example.b3_websecuritycore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
