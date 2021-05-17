package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Table
@Entity
public class AppUser {
    @Id
    private Long userId;
    private String userName;
    private String email;
    private String phone;
    private String account;
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private AppRole appRole;

    public AppUser() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AppRole getAppRole() {
        return appRole;
    }

    public void setAppRole(AppRole appRole) {
        this.appRole = appRole;
    }

    public AppUser(Long userId, String userName, String email, String phone, String account, String password, AppRole appRole) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.account = account;
        this.password = password;
        this.appRole = appRole;
    }
}
