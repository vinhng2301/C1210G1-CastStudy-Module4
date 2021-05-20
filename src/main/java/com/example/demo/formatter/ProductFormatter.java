package com.example.demo.formatter;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class ProductFormatter implements Formatter<Product> {
  private ProductService productService;
  @Autowired
  ProductService productService2;

    public ProductFormatter(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Product parse(String text, Locale locale) throws ParseException {
        Product product = productService2.findById(Long.parseLong(text));
        return product;
    }

    @Override
    public String print(Product object, Locale locale) {
        return object.toString();
    }
}
