package com.example.casestudymodule4.service.impl;

import com.example.casestudymodule4.model.AppUser;
import com.example.casestudymodule4.repository.IAppUserRepository;
import com.example.casestudymodule4.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserServiceImpl implements IAppUserService {
    @Autowired
    IAppUserRepository appUserRepository;
    @Override
    public Optional<AppUser> findByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return appUserRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return appUserRepository.existsByUsername(username);
    }

    @Override
    public Iterable<AppUser> findUsersByNameContaining(String user_name) {
        return appUserRepository.findUsersByNameContaining(user_name);
    }

    @Override
    public AppUser save(AppUser appUser) {
        return appUserRepository.save(appUser);
    }
}
