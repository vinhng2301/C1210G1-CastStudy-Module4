package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class ApiCartController {
    @Autowired
    CartService cartService;

    @GetMapping
    public ResponseEntity<List<Cart>> getAllListCart(HttpSession session) {
        AppUser user = (AppUser) session.getAttribute("user");
        List<Cart> list = (List<Cart>) cartService.getListCartByUserId(user.getUserId());
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        Long indexOfProductInCart = cartService.checkExist(cart);
        if (!indexOfProductInCart.equals(-1L)) {
            int oldQuantity = cart.getQuantity();
            int newQuantity = oldQuantity + cart.getQuantity();
            Long prices = Long.parseLong(cart.getProduct().getPrices()) * newQuantity;
            cart.setQuantity(newQuantity);
            cart.setPrices(prices.toString());
            cart.setNumberId(indexOfProductInCart);
            cartService.save(cart);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            Cart newProductInCart = new Cart(cart.getAppUser(), cart.getProduct(), cart.getQuantity(), cart.getPrices(), cart.getColor(), cart.getSize());
            cartService.save(cart);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    ;

}

