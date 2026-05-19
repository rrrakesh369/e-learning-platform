package com.elearning.notification.service;

import com.elearning.dto.request.NotificationRequest;
import com.elearning.dto.response.NotificationResponse;
import com.elearning.exception.DuplicateResourceException;
import com.elearning.notification.entity.Notification;
import com.elearning.notification.mapper.NotificationMapper;
import com.elearning.notification.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationResponse createNotificationService(NotificationRequest request) {
           boolean existsById= notificationRepository.existsById(request.getId());
           if(existsById){
               log.info("User is Available ");
               throw new DuplicateResourceException("User is already exist with that Id, " + request.getUserId());
           }
          Notification notification= new Notification();
           notification.setId(request.getId());
           notification.setUserId(request.getUserId());
           notification.setType(request.getType());
           notification.setTitle(request.getTitle());
           notification.setMessage(request.getMessage());
           notification.setCreatedAt(request.getCreatedAt());
           notification.setReadAt(request.getReadAt());
           notificationRepository.save(notification);
           log.info("Notification {} is save " + notification.getId());
            return NotificationMapper.toResponse(notification);
    }
}
