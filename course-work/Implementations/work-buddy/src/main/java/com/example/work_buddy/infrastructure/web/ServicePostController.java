package com.example.work_buddy.infrastructure.web;

import com.example.work_buddy.application.dto.ServicePostDto;
import com.example.work_buddy.application.service.interfaces.ServicePostService;
import com.example.work_buddy.domain.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service-posts")
public class ServicePostController {

    private final ServicePostService servicePostService;

    public ServicePostController(ServicePostService servicePostService) {
        this.servicePostService = servicePostService;
    }

    @GetMapping
    public List<ServicePostDto> getAllServicePosts() {
        return servicePostService.getAllServicePosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicePostDto> getServicePostById(@PathVariable String id) {
        return servicePostService.getServicePostById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("ServicePost not found with id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createServicePost(@Valid @RequestBody ServicePostDto servicePostDto) {
        return servicePostService.createServicePost(servicePostDto).id();
    }

    @PutMapping("/{id}")
    public ServicePostDto updateServicePost(@PathVariable String id, @Valid @RequestBody ServicePostDto servicePostDto) {
        return servicePostService.updateServicePost(id, servicePostDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServicePost(@PathVariable String id) {
        servicePostService.deleteServicePost(id);
    }
}
