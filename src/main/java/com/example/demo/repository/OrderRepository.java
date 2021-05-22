package com.example.demo.repository;

import com.example.demo.model.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Orders,Long> {
    Iterable<Orders> findAllByStatus(String status);
    Iterable<Orders> findAllByAppUserNameContainingOrAppUser_PhoneContaining(String key,String key2);
}
