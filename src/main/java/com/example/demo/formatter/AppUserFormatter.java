package com.example.demo.formatter;

import com.example.demo.model.AppUser;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class AppUserFormatter implements Formatter<AppUser> {
   private UserService userService;

    public AppUserFormatter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public AppUser parse(String text, Locale locale) throws ParseException {
        AppUser user = userService.findById(Long.parseLong(text));
        return user;
    }

    @Override
    public String print(AppUser object, Locale locale) {
        return object.toString();
    }
}
