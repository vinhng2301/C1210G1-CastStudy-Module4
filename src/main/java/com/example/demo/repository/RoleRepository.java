package com.example.demo.repository;

import com.example.demo.model.AppRole;
import com.example.demo.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<AppRole,Long> {
    Optional<AppRole> findByName(RoleName name);
}
