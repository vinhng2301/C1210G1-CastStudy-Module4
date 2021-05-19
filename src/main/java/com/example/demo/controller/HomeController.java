package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;
    @RequestMapping("/home")
    public String index(Model model){
        model.addAttribute("products",productService.findAll());
        return "index";
    }

}
