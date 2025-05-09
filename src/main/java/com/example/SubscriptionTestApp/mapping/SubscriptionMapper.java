package com.example.SubscriptionTestApp.mapping;

import com.example.SubscriptionTestApp.dto.request.SubscriptionRequest;
import com.example.SubscriptionTestApp.dto.response.SubscriptionResponse;
import com.example.SubscriptionTestApp.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "userId", source = "user.id")
    SubscriptionResponse toResponse(Subscription subscription);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Subscription toEntity(SubscriptionRequest request);
}
