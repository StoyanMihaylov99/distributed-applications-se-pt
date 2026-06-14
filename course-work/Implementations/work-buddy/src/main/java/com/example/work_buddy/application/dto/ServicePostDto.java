package com.example.work_buddy.application.dto;

import com.example.work_buddy.application.dto.enums.CategoryDto;
import jakarta.validation.constraints.*;

public record   ServicePostDto(String id,
                             @NotBlank(message = "Title is mandatory")
                             @Size(min = 3, max = 60, message = "Title must be between 30 and 60 characters")
                             String title,
                             @NotNull(message = "Category is mandatory")
                             CategoryDto category,
                             @NotBlank(message = "Description is mandatory")
                             @Size(min = 2, max = 1000, message = "Description length must be between 2 and 1000 characters long")
                             String description,
                             @Digits(integer = 6, fraction = 2, message = "The price must be between 1 and 6 integer characters long")
                             @NotNull(message = "Price per hour is mandatory")
                             Double pricePerHour,
                             @NotNull(message = "Location is mandatory")
                             LocationDto location,
                             @NotNull(message = "Post owner is mandatory")
                             String postOwner) {
}
