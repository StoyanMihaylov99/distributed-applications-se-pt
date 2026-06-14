package com.example.work_buddy.application.service;

import com.example.work_buddy.application.mapper.CycleAvoidingMappingContext;
import com.example.work_buddy.application.dto.WorkTaskDto;
import com.example.work_buddy.application.mapper.frontMapper.WorkTaskDtoMapper;
import com.example.work_buddy.application.mapper.backMapper.WorkTaskPersistenceMapper;
import com.example.work_buddy.application.service.interfaces.WorkTaskService;
import com.example.work_buddy.domain.exception.ResourceNotFoundException;
import com.example.work_buddy.domain.repository.UserRepository;
import com.example.work_buddy.domain.repository.WorkTaskRepository;
import com.example.work_buddy.infrastructure.persistence.entity.WorkTask;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkTaskServiceImpl implements WorkTaskService {

    private final WorkTaskRepository workTaskRepository;
    private final UserRepository userRepository;
    private final WorkTaskDtoMapper workTaskDtoMapper;
    private final WorkTaskPersistenceMapper workTaskPersistenceMapper;

    public WorkTaskServiceImpl(WorkTaskRepository workTaskRepository,
                               UserRepository userRepository,
                               WorkTaskDtoMapper workTaskDtoMapper,
                               WorkTaskPersistenceMapper workTaskPersistenceMapper) {
        this.workTaskRepository = workTaskRepository;
        this.userRepository = userRepository;
        this.workTaskDtoMapper = workTaskDtoMapper;
        this.workTaskPersistenceMapper = workTaskPersistenceMapper;
    }

    @Override
    public List<WorkTaskDto> getAllWorkTasks() {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return workTaskRepository.findAll().stream()
                .map(workTask -> workTaskPersistenceMapper.toWorkTaskDomain(workTask, context))
                .map(workTaskDtoMapper::toWorkTaskDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<WorkTaskDto> getWorkTaskById(String id) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return workTaskRepository.findById(id)
                .map(workTask -> workTaskPersistenceMapper.toWorkTaskDomain(workTask, context))
                .map(workTaskDtoMapper::toWorkTaskDto);
    }

    @Override
    public WorkTaskDto createWorkTask(WorkTaskDto workTaskDto) {
        com.example.work_buddy.domain.model.WorkTask domain = workTaskDtoMapper.toWorkTaskDomain(workTaskDto);
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        WorkTask entity = workTaskPersistenceMapper.toWorkTaskEntity(domain, context);

        if (workTaskDto.customer() != null) {
            entity.setCustomer(userRepository.findById(workTaskDto.customer())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + workTaskDto.customer())));
        }

        if (workTaskDto.assignedWorker() != null) {
            entity.setAssignedWorker(userRepository.findById(workTaskDto.assignedWorker())
                    .orElseThrow(() -> new ResourceNotFoundException("Worker not found with id: " + workTaskDto.assignedWorker())));
        }

        WorkTask savedEntity = workTaskRepository.save(entity);
        return workTaskDtoMapper.toWorkTaskDto(workTaskPersistenceMapper.toWorkTaskDomain(savedEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public WorkTaskDto updateWorkTask(String id, WorkTaskDto workTaskDto) {
        if (!workTaskRepository.existsById(id)) {
            throw new ResourceNotFoundException("WorkTask not found with id: " + id);
        }
        com.example.work_buddy.domain.model.WorkTask domain = workTaskDtoMapper.toWorkTaskDomain(workTaskDto);
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        WorkTask entity = workTaskPersistenceMapper.toWorkTaskEntity(domain, context);
        entity.setId(id);

        if (workTaskDto.customer() != null) {
            entity.setCustomer(userRepository.findById(workTaskDto.customer())
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + workTaskDto.customer())));
        }

        if (workTaskDto.assignedWorker() != null) {
            entity.setAssignedWorker(userRepository.findById(workTaskDto.assignedWorker())
                    .orElseThrow(() -> new ResourceNotFoundException("Worker not found with id: " + workTaskDto.assignedWorker())));
        }

        WorkTask savedEntity = workTaskRepository.save(entity);
        return workTaskDtoMapper.toWorkTaskDto(workTaskPersistenceMapper.toWorkTaskDomain(savedEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public void deleteWorkTask(String id) {
        if (!workTaskRepository.existsById(id)) {
            throw new ResourceNotFoundException("WorkTask not found with id: " + id);
        }
        workTaskRepository.deleteById(id);
    }
}
