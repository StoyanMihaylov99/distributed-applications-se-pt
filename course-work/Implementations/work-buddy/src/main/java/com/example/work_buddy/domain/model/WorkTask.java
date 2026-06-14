package com.example.work_buddy.domain.model;

import com.example.work_buddy.domain.enums.Category;
import com.example.work_buddy.domain.enums.TaskStatus;

import java.time.LocalDateTime;

public record WorkTask(String id,
                       String title,
                       String description,
                       Category category,
                       Location location,
                       LocalDateTime dateTime,
                       User customer,
                       User assignedWorker,
                       TaskStatus taskStatus) {
}
