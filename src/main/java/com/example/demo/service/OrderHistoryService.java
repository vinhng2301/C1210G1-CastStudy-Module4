package com.example.demo.service;

import com.example.demo.model.OrderHistory;
import com.example.demo.repository.OrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class OrderHistoryService implements IGeneric<OrderHistory>{
    @Autowired
    OrderHistoryRepository orderHistoryRepository;
    @Override
    public Iterable<OrderHistory> findAll() {
        return null;
    }

    @Override
    public OrderHistory save(OrderHistory orderHistory) {
        return orderHistoryRepository.save(orderHistory);
    }

    @Override
    public OrderHistory findById(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
    public Optional<OrderHistory> findOrderHistoryIdByUserIdAndProductId(Long userId, Long productId){
        return orderHistoryRepository.findOrderHistoryByAppUserUserIdAndProductProductId(userId,productId);
    }
    public Iterable<OrderHistory> findOrderHistoryByOrderId(Long id){
        return  orderHistoryRepository.findOrderHistoryByOrdersOrderId(id);
    }
}
