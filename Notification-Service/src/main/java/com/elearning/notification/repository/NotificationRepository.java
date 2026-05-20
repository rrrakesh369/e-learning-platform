package com.elearning.notification.repository;

import com.elearning.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    boolean existsById(Long Id);
    boolean existsByUserId(String userId);
    List<Notification> findByUserId(String userId);

    List<Notification>
    findByUserIdAndIsRead(String userId,boolean isRead);
}
