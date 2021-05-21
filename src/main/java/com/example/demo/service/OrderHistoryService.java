package com.example.demo.service;

import com.example.demo.model.OrderHistory;
import com.example.demo.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderHistoryService implements IGeneric<OrderHistoryService>{
    @Autowired
    OrderHistoryRepository orderHistoryRepository;
    @Override
    public Iterable<OrderHistoryService> findAll() {
        return null;
    }

    @Override
    public OrderHistoryService save(OrderHistoryService orderHistoryService) {
        return null;
    }

    @Override
    public OrderHistoryService findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
    public Optional<OrderHistory> findOrderHistoryIdByUserIdAndProductId(Long userId, Long productId){
        return orderHistoryRepository.findOrderHistoryByAppUserUserIdAndProductProductId(userId,productId);
    }
}
