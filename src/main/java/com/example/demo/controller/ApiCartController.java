package com.example.demo.controller;

import com.example.demo.model.AppUser;
import com.example.demo.model.Cart;

import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
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
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;

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
        if (indexOfProductInCart != -1) {
            Cart thisProductInCart = cartService.findById(indexOfProductInCart);
            int oldQuantity = thisProductInCart.getQuantity();
            int newQuantity = oldQuantity + cart.getQuantity();
            Long prices = Long.parseLong(productService.findById(cart.getProduct().getProductId()).getPrices()) * newQuantity;
            thisProductInCart.setQuantity(newQuantity);
            thisProductInCart.setPrices(prices.toString());
            cartService.save(thisProductInCart);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            Cart newProductInCart = new Cart(cart.getAppUser(), productService.findById(cart.getProduct().getProductId()), cart.getQuantity(), cart.getPrices(), cart.getColor(), cart.getSize());
            cartService.save(newProductInCart);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<Cart>> showCart(@PathVariable("userId") Long id){
        List<Cart> list = (List<Cart>) cartService.getListCartByUserId(id);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @DeleteMapping("/product")
    public ResponseEntity<Cart> deleteProduct(@RequestBody Cart cart) {
        Long indexThisProduct = cartService.checkExist(cart);
        cartService.delete(indexThisProduct);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("{numberId}/{quantity}/{prices}")
    public ResponseEntity<Cart> updataCart(@PathVariable("numberId") Long id, @PathVariable("quantity") int quantity, @PathVariable("prices") String prices) {
        Cart cart = cartService.findById(id);
        cart.setQuantity(quantity);
        cart.setPrices(prices);
        cartService.save(cart);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

