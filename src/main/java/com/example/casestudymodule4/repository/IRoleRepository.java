package com.example.casestudymodule4.repository;

import com.example.casestudymodule4.model.Role;
import com.example.casestudymodule4.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
