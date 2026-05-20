package com.elearning.notification.service;

import com.elearning.dto.request.NotificationRequest;
import com.elearning.dto.response.NotificationResponse;
import com.elearning.exception.DuplicateResourceException;
import com.elearning.exception.ResourceNotExistException;
import com.elearning.notification.entity.Notification;
import com.elearning.notification.mapper.NotificationMapper;
import com.elearning.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationResponse createNotificationService(NotificationRequest request) {
           boolean existsByUserId= notificationRepository.existsByUserId(request.getUserId());
           if(existsByUserId){
               log.info("User is Available ");
               throw new DuplicateResourceException("User is already exist with that Id, " + request.getUserId());
           }
          Notification notification= new Notification();
           notification.setUserId(request.getUserId());
           notification.setType(request.getType());
           notification.setTitle(request.getTitle());
           notification.setMessage(request.getMessage());
           notificationRepository.save(notification);
           log.info("Notification {} is save " + notification.getId());
            return NotificationMapper.toResponse(notification);
    }
    // Fetch notifications based on userId and unread
    public List<NotificationResponse> getNotifications(String userId, boolean unreadOnly) {

        // List to store notification records
        List<Notification> notifications;
        // Check whether the user exists in notification records
        boolean existsByUser =notificationRepository.existsByUserId(userId);
        // Throw exception if user does not exist
        if (!existsByUser) {
            log.info("User is Not Available");
            throw new ResourceNotExistException("User not found with id : "+ userId);
        }
        // If unreadOnly = true, fetch only unread notifications
        // (isRead = false)
        if (unreadOnly) {
            notifications =notificationRepository.findByUserIdAndIsRead(userId,false);

        } else {
            // Otherwise fetch all notifications for the user
            notifications =notificationRepository.findByUserId(userId);
        }
        // Convert Notification entity list to
        // NotificationResponse DTO list
        return notifications.stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

}
