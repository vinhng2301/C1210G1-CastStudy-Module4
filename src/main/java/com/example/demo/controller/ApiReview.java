package com.example.demo.controller;

import com.example.demo.model.Review;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ApiReview {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/{productId}")
    public ResponseEntity<List<Review>> getReview(@PathVariable("productId") Long id) {
        List<Review> listReviewOfThisProduct = (List<Review>) reviewService.getAllReviewOfProduct(id);
        if (listReviewOfThisProduct.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(listReviewOfThisProduct, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        if (review.getComment().trim() != "") {
            reviewService.save(review);
            return new ResponseEntity<>(review,HttpStatus.OK );
        }
        else  return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
