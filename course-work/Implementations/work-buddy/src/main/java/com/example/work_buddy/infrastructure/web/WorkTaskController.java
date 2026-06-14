package com.example.work_buddy.infrastructure.web;

import com.example.work_buddy.application.dto.WorkTaskDto;
import com.example.work_buddy.application.service.interfaces.WorkTaskService;
import com.example.work_buddy.domain.exception.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work-tasks")
public class WorkTaskController {

    private final WorkTaskService workTaskService;

    public WorkTaskController(WorkTaskService workTaskService) {
        this.workTaskService = workTaskService;
    }

    @GetMapping
    public List<WorkTaskDto> getAllWorkTasks() {
        return workTaskService.getAllWorkTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkTaskDto> getWorkTaskById(@PathVariable String id) {
        return workTaskService.getWorkTaskById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("WorkTask not found with id: " + id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createWorkTask(@Valid @RequestBody WorkTaskDto workTaskDto) {
        return workTaskService.createWorkTask(workTaskDto).id();
    }

    @PutMapping("/{id}")
    public WorkTaskDto updateWorkTask(@PathVariable String id, @Valid @RequestBody WorkTaskDto workTaskDto) {
        return workTaskService.updateWorkTask(id, workTaskDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkTask(@PathVariable String id) {
        workTaskService.deleteWorkTask(id);
    }
}
