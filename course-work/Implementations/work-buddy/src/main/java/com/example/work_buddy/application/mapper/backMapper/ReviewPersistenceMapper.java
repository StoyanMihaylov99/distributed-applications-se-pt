package com.example.work_buddy.application.mapper.backMapper;

import com.example.work_buddy.application.mapper.CycleAvoidingMappingContext;
import com.example.work_buddy.domain.model.Review;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring", uses = {UserPersistenceMapper.class, WorkTaskPersistenceMapper.class})
public interface ReviewPersistenceMapper {

    Review toReviewDomain(com.example.work_buddy.infrastructure.persistence.entity.Review entity, @Context CycleAvoidingMappingContext context);
    com.example.work_buddy.infrastructure.persistence.entity.Review toReviewEntity(Review domain, @Context CycleAvoidingMappingContext context);
    List<Review> toReviewDomainList(List<com.example.work_buddy.infrastructure.persistence.entity.Review> entities, @Context CycleAvoidingMappingContext context);

}
