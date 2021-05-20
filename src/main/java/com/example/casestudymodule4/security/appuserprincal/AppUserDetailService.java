package com.example.casestudymodule4.security.appuserprincal;

import com.example.casestudymodule4.model.AppUser;
import com.example.casestudymodule4.repository.IAppUserRepository;
import com.example.casestudymodule4.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    IAppUserRepository appUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + username)
        );
        return AppUserPrinciple.build(appUser);
    }
}
