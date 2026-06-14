package com.example.work_buddy.application.dto;

import com.example.work_buddy.application.dto.enums.CategoryDto;
import com.example.work_buddy.application.dto.enums.TaskStatusDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

public record WorkTaskDto(String id,
                          @NotBlank
                          @Size(min = 3, max = 60)
                          String title,
                          @NotBlank
                          @Size(max = 1000)
                          String description,
                          @NotNull
                          CategoryDto category,
                          @NotNull
                          LocationDto location,
                          @DateTimeFormat
                          @NotNull
                          LocalDateTime dateTime,
                          @NotNull
                          String customer,
                          String assignedWorker,
                          @NotNull
                          TaskStatusDto taskStatus) {
}
