package com.elearning.dto.response;

import com.elearning.utils.NotificationType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {
    private Long id;
    private String userId;
    private NotificationType type;
    private String title;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime readAt;
    private boolean isRead;

}
