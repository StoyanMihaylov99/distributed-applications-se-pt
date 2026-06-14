package com.example.work_buddy.domain.model;

import com.example.work_buddy.domain.enums.Role;
import java.util.List;

public record User(String id,
                   String firstName,
                   String lastName,
                   String email,
                   String password,
                   Role role,
                   String phoneNumber,
                   List<Review> reviewsAsCustomer,
                   List<Review> reviewsAsWorker,
                   List<WorkTask> tasksAsCustomer,
                   List<WorkTask> tasksAsWorker,
                   List<ServicePost> servicePosts) {
}
