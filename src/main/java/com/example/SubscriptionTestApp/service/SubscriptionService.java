package com.example.SubscriptionTestApp.service;

import com.example.SubscriptionTestApp.entity.Subscription;
import com.example.SubscriptionTestApp.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.SubscriptionTestApp.repository.SubscriptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    public Subscription addSubscription(Long userId, String serviceName) {
        User user = userService.getUserById(userId);
        Subscription subscription = new Subscription();
        subscription.setServiceName(serviceName);
        subscription.setUser(user);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getUserSubscription(Long userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    public List<SubscriptionRepository.TopSubscriptions> getTopSubscriptions(){
        return subscriptionRepository.findTop3Subscriptions();
    }
}
