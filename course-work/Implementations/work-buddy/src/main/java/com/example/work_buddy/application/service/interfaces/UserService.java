package com.example.work_buddy.application.service.interfaces;

import com.example.work_buddy.application.dto.UserDto;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();
    Optional<UserDto> getUserById(String id);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(String id, UserDto userDto);
    void deleteUser(String id);
}
