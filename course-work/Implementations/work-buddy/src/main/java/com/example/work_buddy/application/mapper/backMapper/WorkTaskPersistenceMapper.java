package com.example.work_buddy.application.mapper.backMapper;

import com.example.work_buddy.application.mapper.CycleAvoidingMappingContext;
import com.example.work_buddy.infrastructure.persistence.entity.WorkTask;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = {UserPersistenceMapper.class})
public interface WorkTaskPersistenceMapper {

    com.example.work_buddy.domain.model.WorkTask toWorkTaskDomain(WorkTask entity, @Context CycleAvoidingMappingContext context);
    WorkTask toWorkTaskEntity(com.example.work_buddy.domain.model.WorkTask domain, @Context CycleAvoidingMappingContext context);
    List<com.example.work_buddy.domain.model.WorkTask> toWorkTaskDomainList(List<WorkTask> entities, @Context CycleAvoidingMappingContext context);

}
