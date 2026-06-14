package com.example.work_buddy.application.service.interfaces;

import com.example.work_buddy.application.dto.ServicePostDto;
import java.util.List;
import java.util.Optional;

public interface ServicePostService {
    List<ServicePostDto> getAllServicePosts();
    Optional<ServicePostDto> getServicePostById(String id);
    ServicePostDto createServicePost(ServicePostDto servicePostDto);
    ServicePostDto updateServicePost(String id, ServicePostDto servicePostDto);
    void deleteServicePost(String id);
}
