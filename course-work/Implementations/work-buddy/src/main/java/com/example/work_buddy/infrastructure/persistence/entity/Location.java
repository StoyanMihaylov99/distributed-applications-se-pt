package com.example.work_buddy.infrastructure.persistence.entity;

import com.example.work_buddy.infrastructure.persistence.entity.enums.City;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable
public class Location {

    @NotBlank
    @Size(min = 2, max = 120)
    private String address;

    @NotNull
    @Enumerated(EnumType.STRING)
    private City city;

    @NotBlank
    @Size(min = 1, max = 10)
    private String zipCode;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
