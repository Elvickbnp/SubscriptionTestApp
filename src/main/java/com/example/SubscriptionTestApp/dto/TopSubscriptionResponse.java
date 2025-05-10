package com.example.SubscriptionTestApp.dto;

public record TopSubscriptionResponse(
        String serviceName,
        Long subscriptionsCount
) {}