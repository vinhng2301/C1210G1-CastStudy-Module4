package com.example.casestudymodule4.service;

import com.example.casestudymodule4.model.AppUser;

import java.util.Optional;

public interface IAppUserService {
    Optional<AppUser> findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Iterable<AppUser> findUsersByNameContaining(String user_name);
    AppUser save(AppUser appUser);
}
