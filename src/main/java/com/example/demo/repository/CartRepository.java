package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import org.hibernate.boot.CacheRegionDefinition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findCartsByAppUserUserIdAndAndProductProductId(Long userId, Long productId);
    Iterable<Cart> findCartsByAppUserUserId(Long userId);
}
