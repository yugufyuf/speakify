package com.example.speakify.dto.request;

import com.example.speakify.enums.ActionType;
import com.example.speakify.enums.TargetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data //co the thay the cho @Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivityLogCreationRequest {
    ActionType actionType;
    @NotNull(message = "NOT_NULL")
    String targetId;
    TargetType targetType;
    String description;
}
