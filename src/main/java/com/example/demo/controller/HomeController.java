package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class HomeController {
    @ModelAttribute("user")
    public AppUser appUser() {
        return new AppUser();
    }

    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    CartService cartService;

    @RequestMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("products", productService.findAll());
        return "index";
    }
}
