package com.elearning.dto.response;

import com.elearning.utils.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationStatsResponse {

    private long total;
    private long unread;
    private Map<NotificationType,Long> byType;
}