package com.example.work_buddy.application.mapper.backMapper;

import com.example.work_buddy.application.mapper.CycleAvoidingMappingContext;
import com.example.work_buddy.infrastructure.persistence.entity.User;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {

    com.example.work_buddy.domain.model.User toUserDomain(User entity, @Context CycleAvoidingMappingContext context);

    User toUserEntity(com.example.work_buddy.domain.model.User domain, @Context CycleAvoidingMappingContext context);

    List<com.example.work_buddy.domain.model.User> toUserDomainList(List<User> list, @Context CycleAvoidingMappingContext context);

}
