package com.example.demo.dto.request;
import java.util.Set;

public class SignUpForm {
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
<<<<<<< HEAD
=======

>>>>>>> 3c94f75a9ef3cd211be0f1526bbb8d0087e62e6d

    public SignUpForm() {
    }

    public SignUpForm(String name, String username, String email, String password, String phone) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
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

}
