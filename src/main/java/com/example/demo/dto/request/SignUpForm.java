package com.example.demo.dto.request;
import java.util.Set;

public class SignUpForm {
    private String username;
    private String account;
    private String email;
    private String password;
    private String phone;
    private Set<String> appRole;

    public SignUpForm() {
    }

    public SignUpForm(String username, String account, String email, String password, String phone, Set<String> appRole) {
        this.username = username;
        this.account = account;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.appRole = appRole;
    }

    public String getUsername() {
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

    public String getPassword() {
        return password;
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

    public Set<String> getAppRole() {
        return appRole;
    }

    public void setAppRole(Set<String> appRole) {
        this.appRole = appRole;
    }
}
