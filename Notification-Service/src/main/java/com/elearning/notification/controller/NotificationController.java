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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<CommonResponse<List<NotificationResponse>>> getByUserIsUnread( @RequestParam(name = "userId")String userId,
                                                                                         @RequestParam(name = "unreadOnly"
                                                                                                 ,required = false,defaultValue = "false") boolean unreadOnly)
    {
        List<NotificationResponse> notifications = notificationService.getNotifications(userId,unreadOnly);
        CommonResponse<List<NotificationResponse>> response = new CommonResponse<>();
        response.setCode(200);
        response.setMessage("Notifications fetched");
        response.setData(notifications);
        return ResponseEntity.ok(response);
    }



}
