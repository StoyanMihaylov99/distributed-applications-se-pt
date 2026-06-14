package com.example.work_buddy.application.mapper.frontMapper;

import com.example.work_buddy.application.dto.UserDto;
import com.example.work_buddy.domain.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {WorkTaskDtoMapper.class})
public interface UserDtoMapper {

    UserDto toUserDto(User user);
    User toUserDomain(UserDto userDto);

}
