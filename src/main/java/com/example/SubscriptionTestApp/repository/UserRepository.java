package com.example.SubscriptionTestApp.repository;

import com.example.SubscriptionTestApp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
