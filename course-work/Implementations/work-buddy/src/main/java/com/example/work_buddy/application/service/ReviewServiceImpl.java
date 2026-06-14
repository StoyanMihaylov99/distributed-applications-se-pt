package com.example.work_buddy.application.service;

import com.example.work_buddy.application.mapper.CycleAvoidingMappingContext;
import com.example.work_buddy.application.dto.ReviewDto;
import com.example.work_buddy.application.mapper.frontMapper.ReviewDtoMapper;
import com.example.work_buddy.application.mapper.backMapper.ReviewPersistenceMapper;
import com.example.work_buddy.application.service.interfaces.ReviewService;
import com.example.work_buddy.domain.exception.ResourceNotFoundException;
import com.example.work_buddy.domain.repository.ReviewRepository;
import com.example.work_buddy.domain.repository.UserRepository;
import com.example.work_buddy.domain.repository.WorkTaskRepository;
import com.example.work_buddy.infrastructure.persistence.entity.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final WorkTaskRepository workTaskRepository;
    private final ReviewDtoMapper reviewDtoMapper;
    private final ReviewPersistenceMapper reviewPersistenceMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository,
                             UserRepository userRepository,
                             WorkTaskRepository workTaskRepository,
                             ReviewDtoMapper reviewDtoMapper,
                             ReviewPersistenceMapper reviewPersistenceMapper) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.workTaskRepository = workTaskRepository;
        this.reviewDtoMapper = reviewDtoMapper;
        this.reviewPersistenceMapper = reviewPersistenceMapper;
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return reviewRepository.findAll().stream()
                .map(review -> reviewPersistenceMapper.toReviewDomain(review, context))
                .map(reviewDtoMapper::toReviewDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDto> getReviewById(String id) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return reviewRepository.findById(id)
                .map(review -> reviewPersistenceMapper.toReviewDomain(review, context))
                .map(reviewDtoMapper::toReviewDto);
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        com.example.work_buddy.domain.model.Review domain = reviewDtoMapper.toReviewDomain(reviewDto);
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        Review entity = reviewPersistenceMapper.toReviewEntity(domain, context);

        if (reviewDto.customer() != null) {
            entity.setCustomer(userRepository.findById(reviewDto.customer())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + reviewDto.customer())));
        }

        if (reviewDto.worker() != null) {
            entity.setWorker(userRepository.findById(reviewDto.worker())
                    .orElseThrow(() -> new ResourceNotFoundException("Worker not found with id: " + reviewDto.worker())));
        }

        if (reviewDto.workTask() != null) {
            entity.setWorkTask(workTaskRepository.findById(reviewDto.workTask())
                    .orElseThrow(() -> new ResourceNotFoundException("WorkTask not found with id: " + reviewDto.workTask())));
        }

        Review savedEntity = reviewRepository.save(entity);
        return reviewDtoMapper.toReviewDto(reviewPersistenceMapper.toReviewDomain(savedEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public ReviewDto updateReview(String id, ReviewDto reviewDto) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        com.example.work_buddy.domain.model.Review domain = reviewDtoMapper.toReviewDomain(reviewDto);
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        Review entity = reviewPersistenceMapper.toReviewEntity(domain, context);
        entity.setId(id);

        if (reviewDto.customer() != null) {
            entity.setCustomer(userRepository.findById(reviewDto.customer())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + reviewDto.customer())));
        }

        if (reviewDto.worker() != null) {
            entity.setWorker(userRepository.findById(reviewDto.worker())
                    .orElseThrow(() -> new ResourceNotFoundException("Worker not found with id: " + reviewDto.worker())));
        }

        if (reviewDto.workTask() != null) {
            entity.setWorkTask(workTaskRepository.findById(reviewDto.workTask())
                    .orElseThrow(() -> new ResourceNotFoundException("WorkTask not found with id: " + reviewDto.workTask())));
        }

        Review savedEntity = reviewRepository.save(entity);
        return reviewDtoMapper.toReviewDto(reviewPersistenceMapper.toReviewDomain(savedEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public void deleteReview(String id) {
        if (!reviewRepository.existsById(id)) {
            throw new ResourceNotFoundException("Review not found with id: " + id);
        }
        reviewRepository.deleteById(id);
    }
}
