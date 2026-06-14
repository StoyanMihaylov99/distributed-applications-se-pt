package com.example.work_buddy.domain.repository;

import com.example.work_buddy.infrastructure.persistence.entity.ServicePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicePostRepository extends JpaRepository<ServicePost, String> {
}
