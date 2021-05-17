package com.example.demo.service;

import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class CartService implements IGeneric<Cart> {
    @Autowired
    CartRepository cartRepository;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findById(Long id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);
    }
    //Tim san pham trong cart xem ton tai hay  chua
    public Cart findProductInCart(Long userId,Long productId){
        return cartRepository.findCartsByAppUserUserIdAndAndProductProductId(userId,productId);
    }
    //Dung de hien thi toan bo san pham trong gio  hang
    public Iterable<Cart> getAllListUserCart(Long id){
        return cartRepository.findCartsByAppUserUserId(id);
    }
}
