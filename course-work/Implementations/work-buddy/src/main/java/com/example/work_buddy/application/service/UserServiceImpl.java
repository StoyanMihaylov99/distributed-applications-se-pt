package com.example.work_buddy.application.service;

import com.example.work_buddy.application.mapper.CycleAvoidingMappingContext;
import com.example.work_buddy.application.dto.UserDto;
import com.example.work_buddy.application.mapper.frontMapper.UserDtoMapper;
import com.example.work_buddy.application.mapper.backMapper.UserPersistenceMapper;
import com.example.work_buddy.application.service.interfaces.UserService;
import com.example.work_buddy.domain.exception.DuplicateResourceException;
import com.example.work_buddy.domain.exception.ResourceNotFoundException;
import com.example.work_buddy.domain.repository.UserRepository;
import com.example.work_buddy.infrastructure.persistence.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserPersistenceMapper userPersistenceMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserDtoMapper userDtoMapper, UserPersistenceMapper userPersistenceMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.userPersistenceMapper = userPersistenceMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDto> getAllUsers() {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return userRepository.findAll().stream()
                .map(user -> userPersistenceMapper.toUserDomain(user, context))
                .map(userDtoMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(String id) {
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        return userRepository.findById(id)
                .map(user -> userPersistenceMapper.toUserDomain(user, context))
                .map(userDtoMapper::toUserDto);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.email())) {
            throw new DuplicateResourceException("Email already taken");
        }
        if (userRepository.existsByPhoneNumber(userDto.phoneNumber())) {
            throw new DuplicateResourceException("Phone number already taken");
        }
        com.example.work_buddy.domain.model.User domain = userDtoMapper.toUserDomain(userDto);
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        User entity = userPersistenceMapper.toUserEntity(domain, context);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        User savedEntity = userRepository.save(entity);
        return userDtoMapper.toUserDto(userPersistenceMapper.toUserDomain(savedEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public UserDto updateUser(String id, UserDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (!existingUser.getEmail().equals(userDto.email()) && userRepository.existsByEmail(userDto.email())) {
            throw new DuplicateResourceException("Email already taken");
        }
        if (!existingUser.getPhoneNumber().equals(userDto.phoneNumber()) && userRepository.existsByPhoneNumber(userDto.phoneNumber())) {
            throw new DuplicateResourceException("Phone number already taken");
        }

        com.example.work_buddy.domain.model.User domain = userDtoMapper.toUserDomain(userDto);
        CycleAvoidingMappingContext context = new CycleAvoidingMappingContext();
        User entity = userPersistenceMapper.toUserEntity(domain, context);
        // Ensure the ID from the path is used
        entity.setId(id);
        
        // Only encode password if it is different from existing one (meaning it's a new raw password)
        if (!passwordEncoder.matches(userDto.password(), existingUser.getPassword())) {
            entity.setPassword(passwordEncoder.encode(userDto.password()));
        } else {
            entity.setPassword(existingUser.getPassword());
        }
        
        User savedEntity = userRepository.save(entity);
        return userDtoMapper.toUserDto(userPersistenceMapper.toUserDomain(savedEntity, new CycleAvoidingMappingContext()));
    }

    @Override
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
}
