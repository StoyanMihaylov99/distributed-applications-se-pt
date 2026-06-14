package com.example.work_buddy.application.service.interfaces;

import com.example.work_buddy.application.dto.ReviewDto;
import java.util.List;
import java.util.Optional;

public interface ReviewService {
    List<ReviewDto> getAllReviews();
    Optional<ReviewDto> getReviewById(String id);
    ReviewDto createReview(ReviewDto reviewDto);
    ReviewDto updateReview(String id, ReviewDto reviewDto);
    void deleteReview(String id);
}
