package com.elearning.notification.repository;

import com.elearning.notification.entity.Notification;
import com.elearning.utils.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    boolean existsByUserId(String userId);


    List<Notification> findByUserId(String userId);

    List<Notification>
    findByUserIdAndIsRead(String userId,boolean isRead);

    // Total notifications by user
    long countByUserId(String userId);

    // Total unread notifications
    long countByUserIdAndIsRead(String userId, boolean isRead);

    // Count notifications by type
    long countByUserIdAndType(String userId, NotificationType type);
}
