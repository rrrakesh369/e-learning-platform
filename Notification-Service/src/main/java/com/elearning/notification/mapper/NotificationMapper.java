package com.elearning.notification.mapper;

import com.elearning.dto.response.NotificationResponse;
import com.elearning.notification.entity.Notification;

public class NotificationMapper {

    public static NotificationResponse toResponse(Notification notification){
           NotificationResponse notificationResponse= new NotificationResponse();
           notificationResponse.setId(notification.getId());
           notificationResponse.setUserId(notification.getUserId());
           notificationResponse.setType(notification.getType());
           notificationResponse.setTitle(notification.getTitle());
           notificationResponse.setMessage(notification.getMessage());
           notificationResponse.setCreatedAt(notification.getCreatedAt());
           notificationResponse.setReadAt(notification.getReadAt());
           notificationResponse.setRead(notification.isRead());
           return notificationResponse;

    }
}
