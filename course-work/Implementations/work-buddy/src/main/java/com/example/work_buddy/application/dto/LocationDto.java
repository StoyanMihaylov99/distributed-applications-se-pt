package com.example.work_buddy.application.dto;

import com.example.work_buddy.application.dto.enums.CityDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LocationDto(@NotBlank(message = "Address is mandatory")
                          @Size(min = 2, max = 120)
                          String address,
                          @NotNull(message = "City is mandatory")
                          CityDto city,
                          @NotBlank(message = "Zip code is mandatory")
                          @Size(min = 1, max = 10)
                          String zipCode) {

}
