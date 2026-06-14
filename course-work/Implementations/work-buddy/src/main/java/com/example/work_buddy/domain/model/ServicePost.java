package com.example.work_buddy.domain.model;

import com.example.work_buddy.domain.enums.Category;

public record ServicePost(String id,
                          String title,
                          Category category,
                          String description,
                          Double pricePerHour,
                          Location location,
                          User postOwner) {
}
