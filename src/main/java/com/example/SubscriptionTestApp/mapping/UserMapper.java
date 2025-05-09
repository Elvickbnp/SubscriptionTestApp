package com.example.SubscriptionTestApp.mapping;

import com.example.SubscriptionTestApp.dto.request.UserRequest;
import com.example.SubscriptionTestApp.dto.response.UserResponse;
import com.example.SubscriptionTestApp.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "subscriptions", source = "subscriptions")
    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "subscriptions", ignore = true)
    User toEntity(UserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequest(UserRequest request, @MappingTarget User user);
}
