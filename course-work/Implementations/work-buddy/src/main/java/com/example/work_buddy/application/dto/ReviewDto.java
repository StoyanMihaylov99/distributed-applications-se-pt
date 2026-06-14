package com.example.work_buddy.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

public record ReviewDto(String id,
                        @NotBlank(message = "Title is mandatory")
                        @Size(min = 3, max = 60, message = "Title must be between 3 and 60 characters")
                        String title,
                        @NotBlank(message = "Description is mandatory")
                        @Size(max = 1000, message = "Description cannot exceed 1000 characters")
                        String description,
                        @NotNull(message = "Rating is mandatory")
                        @Min(value = 1, message = "Rating must be at least 1")
                        @Max(value = 5, message = "Rating cannot be more than 5")
                        Double rating,
                        @NotNull(message = "Customer information is mandatory")
                        String customer,
                        @NotNull(message = "Worker information is mandatory")
                        String worker,
                        @NotNull(message = "Work task reference is mandatory")
                        String workTask) {
}
