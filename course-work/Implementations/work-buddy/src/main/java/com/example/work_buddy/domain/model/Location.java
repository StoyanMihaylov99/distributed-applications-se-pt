package com.example.work_buddy.domain.model;

import com.example.work_buddy.domain.enums.City;

public record Location(String address,
                       City city,
                       String zipCode) {

}
