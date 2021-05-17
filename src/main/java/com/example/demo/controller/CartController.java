package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    @RequestMapping("/addToCart/{id}")
    public String getCart(@PathVariable("id") Long id, HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");
        Product product = productService.findById(id);
        Cart cart = cartService.findProductInCart(user.getUserId(), product.getProductId());
        if (cart == null) {
            cartService.save(new Cart(user, product, 1, product.getPrices()));
            return "/index";
        } else {
            int oldQuantity = cart.getQuantity();
            int newQuantity = oldQuantity + 1;
            cart.setQuantity(newQuantity);
            Long prices = Long.parseLong(cart.getProduct().getPrices()) * newQuantity;
            cartService.save(new Cart(cart.getNumberId(), user, product, newQuantity, prices.toString()));
            return "/index";
        }
    }

    @GetMapping("/cart/{userId}")
    public String getCartOfUser(@PathVariable Long id, Model model) {
        Iterable<Cart> cart = cartService.getAllListUserCart(id);
        model.addAttribute("carts",cart);
        return "/cart";
    }
}

