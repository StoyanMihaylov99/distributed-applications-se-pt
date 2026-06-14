package com.example.work_buddy.application.mapper.frontMapper;

import com.example.work_buddy.application.dto.ServicePostDto;
import com.example.work_buddy.domain.model.ServicePost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring", uses = {WorkTaskDtoMapper.class})
public interface ServicePostDtoMapper {

    ServicePostDto toServicePostDto(ServicePost servicePost);

    @Mapping(target = "postOwner", ignore = true)
    ServicePost toServicePostDomain(ServicePostDto servicePostDto);

    List<ServicePostDto> toServicePostDtoList(List<ServicePost> list);

}
