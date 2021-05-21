package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService implements IGeneric<Review> {
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review findById(Long id) {
        return reviewRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        reviewRepository.deleteById(id);
    }

    public Iterable<Review> getAllReviewOfProduct(Long id) {
        return reviewRepository.findReviewsByProductProductId(id);
    }
}
