package repository;

import Entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUserId(Long userId);

    @Query(value = """
    SELECT s.service_name as serviceName, Count(*) as count 
    FROM subscriptions s 
    GROUP BY s.service_name
    ORDER BY count DESC 
    LIMIT 3""",
    nativeQuery = true)
    List<TopSubscriptions> findTop3Subscriptions();

    interface TopSubscriptions {
        String getServiceName();
        Long getCount();
    }
}
