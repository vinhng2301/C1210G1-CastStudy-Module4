package com.example.demo.controller;

import com.example.demo.model.OrderHistory;
import com.example.demo.model.Review;
import com.example.demo.service.OrderHistoryService;
import com.example.demo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/review")
public class ApiReview {
    @Autowired
    ReviewService reviewService;
    @Autowired
    OrderHistoryService orderHistoryService;
    @GetMapping("/{productId}")
    public ResponseEntity<List<Review>> getReview(@PathVariable("productId") Long id) {
        List<Review> listReviewOfThisProduct = (List<Review>) reviewService.getAllReviewOfProduct(id);
        if (listReviewOfThisProduct.isEmpty()) {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        } else return new ResponseEntity<>(listReviewOfThisProduct, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Review> addReview(@RequestBody Review review) {
        reviewService.save(review);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/{userId}/{productId}")
    public ResponseEntity<Boolean> checkAllowedToComment(@PathVariable("userId") Long userId , @PathVariable("productId") Long productId) {
        //chi nhung nguoi mua san pham moi duoc comment\
        Optional<OrderHistory> orderHistory = orderHistoryService.findOrderHistoryIdByUserIdAndProductId(userId,productId);
        if(!orderHistory.isPresent() || !orderHistory.get().getStatus().equalsIgnoreCase("done")){
            return new ResponseEntity<>(false,HttpStatus.OK);
        }
        else return new ResponseEntity<>(true,HttpStatus.OK);

    }
}
