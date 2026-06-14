package com.example.work_buddy.application.mapper.backMapper;

import com.example.work_buddy.application.mapper.CycleAvoidingMappingContext;
import com.example.work_buddy.infrastructure.persistence.entity.ServicePost;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserPersistenceMapper.class})
public interface ServicePostPersistenceMapper {

    com.example.work_buddy.domain.model.ServicePost toServicePostDomain(ServicePost entity, @Context CycleAvoidingMappingContext context);
    ServicePost toServicePostEntity(com.example.work_buddy.domain.model.ServicePost domain, @Context CycleAvoidingMappingContext context);
    List<com.example.work_buddy.domain.model.ServicePost> toServicePostDomainList(List<ServicePost> entities, @Context CycleAvoidingMappingContext context);

}
