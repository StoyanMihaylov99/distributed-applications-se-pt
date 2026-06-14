package com.example.work_buddy.infrastructure.web;

import com.example.work_buddy.application.dto.ReviewDto;
import com.example.work_buddy.application.service.interfaces.ReviewService;
import com.example.work_buddy.domain.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable String id) {
        return reviewService.getReviewById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found with id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createReview(@Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto).id();
    }

    @PutMapping("/{id}")
    public ReviewDto updateReview(@PathVariable String id, @Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.updateReview(id, reviewDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
    }
}
