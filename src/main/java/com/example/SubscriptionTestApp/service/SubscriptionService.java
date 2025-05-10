package com.example.SubscriptionTestApp.service;

import com.example.SubscriptionTestApp.dto.TopSubscriptionResponse;
import com.example.SubscriptionTestApp.dto.request.SubscriptionRequest;
import com.example.SubscriptionTestApp.dto.response.SubscriptionResponse;
import com.example.SubscriptionTestApp.entity.Subscription;
import com.example.SubscriptionTestApp.entity.User;
import com.example.SubscriptionTestApp.mapping.SubscriptionMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.SubscriptionTestApp.repository.SubscriptionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;
    private final SubscriptionMapper subscriptionMapper;

    public SubscriptionResponse addSubscription(Long userId, SubscriptionRequest request) {
        User user = userService.getUserEntityById(userId); // Получаем сущность User

        Subscription subscription = subscriptionMapper.toEntity(request);
        subscription.setUser(user);

        Subscription savedSubscription = subscriptionRepository.save(subscription);
        log.info("Add subscription: {}", subscription.getServiceName());
        return subscriptionMapper.toResponse(savedSubscription);
    }

    public List<SubscriptionResponse> getUserSubscription(Long userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        return subscriptions.stream()
                .map(subscriptionMapper::toResponse)
                .toList();
    }

    public void deleteSubscription(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
        log.info("Delete subscription: {}", subscriptionId);
    }

    public List<TopSubscriptionResponse> getTopSubscriptions() {
        Pageable topThree = PageRequest.of(0, 3); // LIMIT 3
        return subscriptionRepository.findTop3Subscriptions(topThree);
    }
}
