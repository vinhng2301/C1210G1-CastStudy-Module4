package com.example.demo.dto.request;
import java.util.Set;

public class SignUpForm {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private Set<String> appRole;

    public SignUpForm() {
    }

    public SignUpForm(String name, String username, String email, String password, String phone, Set<String> appRole) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.appRole = appRole;
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
