
package com.example.demo.dto.reponse;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    private Long userId;
    private String token;
    private String type = "Bearer";
    private String name;
    private String username;
    private String email;
    private String phone;
    private Collection<? extends GrantedAuthority> appRole;

    public JwtResponse(String token, String name, String username, String email, String phone, Collection<? extends GrantedAuthority> appRole) {
        this.token = token;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.appRole = appRole;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
