package com.example.SubscriptionTestApp.dto.response;

import java.util.List;

public record UserResponse(
        Long id,
        String name,
        String email,
        List<SubscriptionResponse> subscriptions) {}
