package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.Warehouse;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import com.example.demo.service.WareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/warehouse")
public class ApiWareController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;
    @Autowired
    WareService wareService;
    @GetMapping("/{size}/{productId}")
    public ResponseEntity<Iterable<Warehouse>> responseEntity(@PathVariable("size") String size, @PathVariable("productId") Long id) {
        Iterable<Warehouse> warehouses = wareService.selectProductInWareBySizeAndProductId(size, id);
        return new ResponseEntity<>(warehouses, HttpStatus.OK);
    }
    @GetMapping("/{key}")
    public ResponseEntity<Iterable<Product>> search(@PathVariable String key){
        List<Product> list = (List<Product>) productService.listProductFindByName(key);
        if(list.isEmpty()){
            return new ResponseEntity<>(productService.findAll(),HttpStatus.OK);
        }
        else  return  new ResponseEntity<>(list,HttpStatus.OK);
    }
}
