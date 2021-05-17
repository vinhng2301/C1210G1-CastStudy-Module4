package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;

    @RequestMapping("/home/{id}")
    public String index(Model model, @PathVariable("id") Long id) {
        AppUser user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("products", productService.findAll());
        return "/home";
    }
}
