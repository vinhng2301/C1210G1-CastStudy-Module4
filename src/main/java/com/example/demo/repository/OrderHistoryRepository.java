package com.example.demo.repository;

import com.example.demo.model.OrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    Optional<OrderHistory> findOrderHistoryByAppUserUserIdAndProductProductId(Long userId , Long productId);
}
