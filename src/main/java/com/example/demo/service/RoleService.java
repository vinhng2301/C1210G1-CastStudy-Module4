package com.example.demo.service;

import com.example.demo.model.AppRole;
import com.example.demo.model.RoleName;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RoleService {
    @Autowired
    RoleRepository  roleRepository;
    public Optional<AppRole> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
