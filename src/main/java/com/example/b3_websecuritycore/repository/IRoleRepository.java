package com.example.b3_websecuritycore.repository;

import com.example.b3_websecuritycore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
