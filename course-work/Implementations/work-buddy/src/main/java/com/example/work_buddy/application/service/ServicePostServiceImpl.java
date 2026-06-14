package com.example.work_buddy.application.service;

import com.example.work_buddy.application.mapper.CycleAvoidingMappingContext;
import com.example.work_buddy.application.dto.ServicePostDto;
import com.example.work_buddy.application.mapper.frontMapper.ServicePostDtoMapper;
import com.example.work_buddy.application.mapper.backMapper.ServicePostPersistenceMapper;
import com.example.work_buddy.application.service.interfaces.ServicePostService;
import com.example.work_buddy.domain.exception.ResourceNotFoundException;
import com.example.work_buddy.domain.repository.ServicePostRepository;
import com.example.work_buddy.domain.repository.UserRepository;
import com.example.work_buddy.infrastructure.persistence.entity.ServicePost;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServicePostServiceImpl implements ServicePostService {

    private final ServicePostRepository servicePostRepository;
    private final UserRepository userRepository;
    private final ServicePostDtoMapper servicePostDtoMapper;
    private final ServicePostPersistenceMapper servicePostPersistenceMapper;

    public ServicePostServiceImpl(ServicePostRepository servicePostRepository,
                                  UserRepository userRepository,
                                  ServicePostDtoMapper servicePostDtoMapper,
                                  ServicePostPersistenceMapper servicePostPersistenceMapper) {
        this.servicePostRepository = servicePostRepository;
        this.userRepository = userRepository;
        this.servicePostDtoMapper = servicePostDtoMapper;
        this.servicePostPersistenceMapper = servicePostPersistenceMapper;
    }

    @Override
    public List<ServicePostDto> getAllServicePosts() {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return servicePostRepository.findAll().stream()
                .map(post -> servicePostPersistenceMapper.toServicePostDomain(post, context))
                .map(servicePostDtoMapper::toServicePostDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ServicePostDto> getServicePostById(String id) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return servicePostRepository.findById(id)
                .map(post -> servicePostPersistenceMapper.toServicePostDomain(post, context))
                .map(servicePostDtoMapper::toServicePostDto);
    }

    @Override
    public ServicePostDto createServicePost(ServicePostDto servicePostDto) {
        com.example.work_buddy.domain.model.ServicePost domain = servicePostDtoMapper.toServicePostDomain(servicePostDto);
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        ServicePost entity = servicePostPersistenceMapper.toServicePostEntity(domain, context);

        if (servicePostDto.postOwner() != null) {
            entity.setPostOwner(userRepository.findById(servicePostDto.postOwner())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + servicePostDto.postOwner())));
        }

        ServicePost savedEntity = servicePostRepository.save(entity);
        return servicePostDtoMapper.toServicePostDto(servicePostPersistenceMapper.toServicePostDomain(savedEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public ServicePostDto updateServicePost(String id, ServicePostDto servicePostDto) {
        if (!servicePostRepository.existsById(id)) {
            throw new ResourceNotFoundException("ServicePost not found with id: " + id);
        }
        com.example.work_buddy.domain.model.ServicePost domain = servicePostDtoMapper.toServicePostDomain(servicePostDto);
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        ServicePost entity = servicePostPersistenceMapper.toServicePostEntity(domain, context);
        entity.setId(id);

        if (servicePostDto.postOwner() != null) {
            entity.setPostOwner(userRepository.findById(servicePostDto.postOwner())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + servicePostDto.postOwner())));
        }

        ServicePost savedEntity = servicePostRepository.save(entity);
        return servicePostDtoMapper.toServicePostDto(servicePostPersistenceMapper.toServicePostDomain(savedEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public void deleteServicePost(String id) {
        if (!servicePostRepository.existsById(id)) {
            throw new ResourceNotFoundException("ServicePost not found with id: " + id);
        }
        servicePostRepository.deleteById(id);
    }
}
