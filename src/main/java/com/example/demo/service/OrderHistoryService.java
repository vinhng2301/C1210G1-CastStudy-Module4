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
        return orderHistoryRepository.findAll();
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
    public Iterable<OrderHistory> findOrderHistoryByUserIdAndProductId(Long userId, Long productId){
        return orderHistoryRepository.findOrderHistoriesByAppUserUserIdAndProductProductId(userId,productId);
    }
    public Iterable<OrderHistory> findOrderHistoryByOrderId(Long id ){
        return   orderHistoryRepository.findOrderHistoriesByOrdersOrderId(id);
    }

}
