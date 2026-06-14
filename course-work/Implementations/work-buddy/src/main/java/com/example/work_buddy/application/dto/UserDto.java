package com.example.work_buddy.application.dto;

import com.example.work_buddy.application.dto.enums.RoleDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserDto(String id,
                      @NotBlank
                      @Size(min = 3, max = 20)
                      String firstName,
                      @NotBlank
                      @Size(min = 3, max = 20)
                      String lastName,
                      @NotBlank
                      @Email
                      String email,
                      @NotBlank
                      @Size(min = 8)
                      String password,
                      @NotNull
                      RoleDto role,
                      @NotBlank
                      @Size(min = 10, max = 10)
                      String phoneNumber,
                      List<ReviewDto> reviewsAsCustomer,
                      List<ReviewDto> reviewsAsWorker,
                      List<WorkTaskDto> tasksAsCustomer,
                      List<WorkTaskDto> tasksAsWorker,
                      List<ServicePostDto> servicePosts) {
}
