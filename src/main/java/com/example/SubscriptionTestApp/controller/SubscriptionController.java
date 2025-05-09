package com.example.SubscriptionTestApp.controller;

import com.example.SubscriptionTestApp.entity.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.SubscriptionTestApp.repository.SubscriptionRepository;
import com.example.SubscriptionTestApp.service.SubscriptionService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users/{userId}/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<Subscription> addSubscription(@PathVariable Long userId, @RequestBody
    Map<String, String> request) {
        String serviceName = request.get("serviceName");
        return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionService.addSubscription(userId, serviceName));
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getSubscription(@PathVariable Long userId){
        return ResponseEntity.ok(subscriptionService.getUserSubscription(userId));
    }

    @DeleteMapping("/{subscriptionId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long subscriptionId){
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/top")
    public ResponseEntity<List<SubscriptionRepository.TopSubscriptions>> getTopSubscriptions(){
        return ResponseEntity.ok(subscriptionService.getTopSubscriptions());
    }
}
