package com.example.demo.service;

import com.example.demo.model.Orders;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IGeneric<Orders> {
    @Autowired
    OrderRepository orderRepository;
    public Page<Orders> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Iterable<Orders> findAll() {
        return null;
    }

    @Override
    public Orders save(Orders orders) {
        return  orderRepository.save(orders);
    }

    @Override
    public Orders findById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {

    }
}
