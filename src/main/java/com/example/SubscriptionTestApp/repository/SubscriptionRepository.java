package com.example.SubscriptionTestApp.repository;

import com.example.SubscriptionTestApp.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);

    @Query("""
    SELECT s.service_name as serviceName, Count(*) as count
    FROM subscriptions s
    GROUP BY s.service_name
    ORDER BY count DESC
    LIMIT 3"""
    )
    List<TopSubscriptions> findTop3Subscriptions();

    interface TopSubscriptions {
        String getServiceName();
        Long getCount();
    }
}
