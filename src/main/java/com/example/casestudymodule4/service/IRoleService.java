package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.Role;
import com.example.casestudymodule4.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName name);
}
