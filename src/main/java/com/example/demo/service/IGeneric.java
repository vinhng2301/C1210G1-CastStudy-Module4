package com.example.demo.service;

public interface IGeneric <T>{
    Iterable<T>  findAll();
    T save(T t);
    T findById(Long id);
    void delete(Long id);
}
