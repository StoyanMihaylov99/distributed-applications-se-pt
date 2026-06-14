package com.example.work_buddy.domain.repository;


import com.example.work_buddy.infrastructure.persistence.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {

}
