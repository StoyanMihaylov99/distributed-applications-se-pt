package com.example.work_buddy.application.service.interfaces;

import com.example.work_buddy.application.dto.WorkTaskDto;
import java.util.List;
import java.util.Optional;

public interface WorkTaskService {
    List<WorkTaskDto> getAllWorkTasks();
    Optional<WorkTaskDto> getWorkTaskById(String id);
    WorkTaskDto createWorkTask(WorkTaskDto workTaskDto);
    WorkTaskDto updateWorkTask(String id, WorkTaskDto workTaskDto);
    void deleteWorkTask(String id);
}
