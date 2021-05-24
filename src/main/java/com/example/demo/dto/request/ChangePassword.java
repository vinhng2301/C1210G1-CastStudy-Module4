package com.example.demo.dto.request;

public class ChangePassword {
    private String number;
    private String password;
    private String rePassword;

    public ChangePassword() {
    }

    public ChangePassword(String number, String password, String rePassword) {
        this.number = number;
        this.password = password;
        this.rePassword = rePassword;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
