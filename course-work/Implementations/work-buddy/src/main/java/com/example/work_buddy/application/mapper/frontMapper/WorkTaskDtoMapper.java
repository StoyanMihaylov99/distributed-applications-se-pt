package com.example.work_buddy.application.mapper.frontMapper;

import com.example.work_buddy.application.dto.WorkTaskDto;
import com.example.work_buddy.domain.model.User;
import com.example.work_buddy.domain.model.WorkTask;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkTaskDtoMapper {

    WorkTaskDto toWorkTaskDto(WorkTask workTask);

    @Mapping(target = "customer", ignore = true)
    @Mapping(target = "assignedWorker", ignore = true)
    WorkTask toWorkTaskDomain(WorkTaskDto workTaskDto);

    List<WorkTaskDto> toWorkTaskDtoList(List<WorkTask> list);

    default String map(User user) {
        return user != null ? user.id() : null;
    }

    default User map(String id) {
        if (id == null) return null;
        return new User(id, null, null, null, null, null, null, null, null, null, null, null);
    }

    default String mapWorkTaskToId(WorkTask workTask) {
        return workTask != null ? workTask.id() : null;
    }

    default WorkTask mapIdToWorkTask(String id) {
        if (id == null) return null;
        return new WorkTask(id, null, null, null, null, null, null, null, null);
    }

}
