package com.example.SubscriptionTestApp.repository;

import com.example.SubscriptionTestApp.dto.TopSubscriptionResponse;
import com.example.SubscriptionTestApp.entity.Subscription;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);

    @Query("""
            SELECT NEW com.example.SubscriptionTestApp.dto.TopSubscriptionResponse(
                s.serviceName,
                COUNT(s) AS subscriptionsCount
            )
            FROM Subscription s
            GROUP BY s.serviceName
            ORDER BY COUNT(s) DESC"""
    )
    List<TopSubscriptionResponse> findTop3Subscriptions(Pageable topThree);

    interface TopSubscriptions {
        String getServiceName();

        Long getCount();
    }
}
