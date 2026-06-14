package com.example.work_buddy.domain.model;

public record Review(String id,
                     String title,
                     String description,
                     Double rating,
                     User customer,
                     User worker,
                     WorkTask workTask) {
}
