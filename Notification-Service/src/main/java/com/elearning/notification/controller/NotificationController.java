package com.elearning.notification.controller;

import com.elearning.core.dto.CommonResponse;
import com.elearning.dto.request.NotificationRequest;
import com.elearning.dto.response.NotificationResponse;
import com.elearning.notification.repository.NotificationRepository;
import com.elearning.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@AllArgsConstructor
public class NotificationController {

private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<CommonResponse<NotificationResponse>> createNotification(@Valid @RequestBody NotificationRequest request){
       NotificationResponse notificationResponse= notificationService.createNotificationService(request);
      CommonResponse<NotificationResponse> commonResponse= new CommonResponse<>();
      commonResponse.setData(notificationResponse);
      commonResponse.setMessage("Notification Create Success");
      commonResponse.setCode(201);
        return ResponseEntity.ok(commonResponse);
    }
}
