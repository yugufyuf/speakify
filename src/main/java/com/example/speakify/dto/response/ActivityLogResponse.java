package com.example.speakify.dto.response;

import com.example.speakify.enums.ActionType;
import com.example.speakify.enums.TargetType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityLogResponse {
    ActionType actionType;
    String targetId;
    TargetType targetType;
    String description;
    LocalDateTime createdAt;
    Object targetDetails;
}
