package com.example.SubscriptionTestApp.mapping;

import com.example.SubscriptionTestApp.dto.request.UserRequest;
import com.example.SubscriptionTestApp.dto.response.UserResponse;
import com.example.SubscriptionTestApp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.BeanMapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    User toEntity(UserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequest(UserRequest request, @MappingTarget User user);
}
