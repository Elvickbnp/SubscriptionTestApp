package com.example.SubscriptionTestApp.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SubscriptionRequest(
        @NotBlank(message = "Service name is required")
        String serviceName
) {}
