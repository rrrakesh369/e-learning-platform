package com.elearning.notification.service;

import com.elearning.dto.request.NotificationRequest;
import com.elearning.dto.response.NotificationResponse;
import com.elearning.dto.response.NotificationStatsResponse;
import com.elearning.exception.DuplicateResourceException;
import com.elearning.exception.ResourceNotExistException;
import com.elearning.notification.entity.Notification;
import com.elearning.notification.mapper.NotificationMapper;
import com.elearning.notification.repository.NotificationRepository;
import com.elearning.utils.NotificationType;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationResponse createNotificationService(NotificationRequest request) {
        // Check if notification already exists for the given user ID
           boolean existsByUserId= notificationRepository.existsByUserId(request.getUserId());

        // If user already exists, log information and throw duplicate exception
           if(existsByUserId){
               log.info("User is Available ");
               throw new DuplicateResourceException("User is already exist with this userId, " + request.getUserId());
           }

        // Create new Notification object
          Notification notification= new Notification();

        // Set notification details from request object
           notification.setUserId(request.getUserId());
           notification.setType(request.getType());
           notification.setTitle(request.getTitle());
           notification.setMessage(request.getMessage());

        // Save notification into database
           notificationRepository.save(notification);

        // Log notification save operation with generated notification ID
           log.info("Notification {} is save " + notification.getId());

        // Convert entity to response DTO and return response
            return NotificationMapper.toResponse(notification);
    }



    public List<NotificationResponse> getNotifications(String userId, boolean unreadOnly) {

        // List to store notification records
        List<Notification> notifications;

        // Check whether the user exists in notification records
        boolean existsByUser =notificationRepository.existsByUserId(userId);

        // Throw exception if user does not exist
        if (!existsByUser) {
            log.info("User is Not Available");
            throw new ResourceNotExistException("User not found with userId : "+ userId);
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



    public NotificationResponse markAsRead(Long id){

        boolean existsById =notificationRepository.existsById(id);

        // Throw exception if user does not exist
        if (!existsById) {
            log.info("Id is Not Available");
            throw new ResourceNotExistException("Id not found with id : "+ id);
        }

        // Check notification exists or not
        Notification notification =notificationRepository.findById(id).orElseThrow(() ->new ResourceNotExistException("Notification not found with id : "+ id));

        // Update notification read status
        notification.setRead(true);

        // Set read timestamp
        notification.setReadAt(LocalDateTime.now());

        // Save updated notification
        Notification updatedNotification = notificationRepository.save(notification);

        // Convert entity to response DTO
        return NotificationMapper.toResponse(updatedNotification);
    }


    public NotificationStatsResponse getNotificationStats(String userId) {

        // Check user exists
        boolean existsByUser =notificationRepository.existsByUserId(userId);

        if (!existsByUser) {
            log.info("User not available");

            throw new ResourceNotExistException("User not found with id : " + userId);
        }

        // Total notifications
        long total = notificationRepository.countByUserId(userId);

        // Total unread notifications
        long unread = notificationRepository.countByUserIdAndIsRead(userId,false);

        // Notification count by type
        Map<NotificationType,Long> byType = new HashMap<>();

        for (NotificationType type :NotificationType.values()) {

            long count =notificationRepository.countByUserIdAndType(userId,type);
            byType.put(type,count);
        }

        return new NotificationStatsResponse(total,unread,byType);
    }
}
