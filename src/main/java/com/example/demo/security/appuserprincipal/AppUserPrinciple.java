package com.example.demo.security.appuserprincipal;



import com.example.demo.model.AppUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AppUserPrinciple implements UserDetails {
    private Long userId;
    private String username;
    private String account;
    private String email;
    @JsonIgnore
    private String password;
    private String phone;
    private Collection<? extends GrantedAuthority> appRole;

    public AppUserPrinciple(Long userId, String username, String account, String email, String password, String phone, Collection<? extends GrantedAuthority> appRole) {
        this.userId = userId;
        this.username = username;
        this.account = account;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.appRole = appRole;
    }

    public static AppUserPrinciple build(AppUser appuser) {
        List<GrantedAuthority> authorities = appuser.getAppRole().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())
        ).collect(Collectors.toList());

        return new AppUserPrinciple(
                appuser.getUserId(),
                appuser.getUsername(),
                appuser.getAccount(),
                appuser.getEmail(),
                appuser.getPassword(),
                appuser.getPhone(),
                authorities
        );
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getusername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appRole;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<? extends GrantedAuthority> getAppRole() {
        return appRole;
    }

    public void setAppRole(Collection<? extends GrantedAuthority> appRole) {
        this.appRole = appRole;
    }
}
