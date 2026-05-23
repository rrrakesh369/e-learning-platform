package com.elearning.notification.controller;

import com.elearning.core.dto.CommonResponse;
import com.elearning.dto.request.NotificationRequest;
import com.elearning.dto.response.NotificationResponse;
import com.elearning.dto.response.NotificationStatsResponse;
import com.elearning.exception.ResourceNotExistException;
import com.elearning.notification.repository.NotificationRepository;
import com.elearning.notification.service.NotificationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
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

    @PatchMapping("/{id}/read")
    public ResponseEntity<CommonResponse<NotificationResponse>>markAsRead(@PathVariable(name = "id") Long id){
            NotificationResponse responseDto =notificationService.markAsRead(id);
            CommonResponse<NotificationResponse> response =new CommonResponse<>();
            response.setCode(200);
            response.setMessage("Notification marked as read");
            response.setData(responseDto);
            return ResponseEntity.ok(response);

    }

    @GetMapping("/stats")
    public ResponseEntity<CommonResponse<NotificationStatsResponse>>

    getStats(@RequestParam(name = "userId")String userId){
        NotificationStatsResponse stats =notificationService.getNotificationStats(userId);
        CommonResponse<NotificationStatsResponse> response = new CommonResponse<>();
        response.setCode(200);
        response.setMessage("Notification statistics fetched");
        response.setData(stats);
        return ResponseEntity.ok(response);

    }
}
