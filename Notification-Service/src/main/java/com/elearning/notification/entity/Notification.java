package com.elearning.notification.entity;

import com.elearning.utils.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "userId is required")
    @Column(nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NotificationType type;

    @NotBlank(message = "title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "message is required")
    @Column(nullable = false)
    private String message;

    private boolean isRead = false;

    private LocalDateTime createdAt;

    private LocalDateTime readAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        isRead = false;
    }
}
