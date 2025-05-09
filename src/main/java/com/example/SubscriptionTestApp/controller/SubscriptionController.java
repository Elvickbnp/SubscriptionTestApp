package com.example.SubscriptionTestApp.controller;

import com.example.SubscriptionTestApp.dto.request.SubscriptionRequest;
import com.example.SubscriptionTestApp.dto.response.SubscriptionResponse;
import com.example.SubscriptionTestApp.entity.Subscription;
import com.example.SubscriptionTestApp.mapping.SubscriptionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.SubscriptionTestApp.repository.SubscriptionRepository;
import com.example.SubscriptionTestApp.service.SubscriptionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private SubscriptionMapper subscriptionMapper;

    @PostMapping("/users/{userId}/subscriptions")
    public ResponseEntity<SubscriptionResponse> addSubscription(@PathVariable Long userId,
    @Valid @RequestBody SubscriptionRequest request) {
        SubscriptionResponse response = subscriptionService.addSubscription(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/users/{userId}/subscriptions")
    public ResponseEntity<List<SubscriptionResponse>> getSubscription(@PathVariable Long userId){
        List<SubscriptionResponse> responses = subscriptionService.getUserSubscription(userId);
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/users/{userId}/subscriptions/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long subscriptionId){
        return ResponseEntity.noContent().build();
    }

    @GetMapping("subscriptions/top")
    public ResponseEntity<List<SubscriptionRepository.TopSubscriptions>> getTopSubscriptions(){
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }
}
