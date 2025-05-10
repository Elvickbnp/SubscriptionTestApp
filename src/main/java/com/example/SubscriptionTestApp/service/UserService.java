package com.example.SubscriptionTestApp.service;

import com.example.SubscriptionTestApp.dto.request.UserRequest;
import com.example.SubscriptionTestApp.dto.response.UserResponse;
import com.example.SubscriptionTestApp.entity.User;
import com.example.SubscriptionTestApp.exception.UserNotFoundException;
import com.example.SubscriptionTestApp.mapping.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.SubscriptionTestApp.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;

    public UserResponse createUser(UserRequest request){
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        log.info("Creating user: {}", user.getEmail());
        return userMapper.toResponse(savedUser);
    }

    public UserResponse getUserById(Long id){
        User user =userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toResponse(user);
    }

    public User getUserEntityById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public UserResponse updateUser(Long id, UserRequest request){
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userMapper.updateFromRequest(request, existingUser);
        User updatedUser = userRepository.save(existingUser);

        log.info("Update user: {}", id);
        return userMapper.toResponse(updatedUser);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
        log.info("Delete user: {}", id);
    }
}
