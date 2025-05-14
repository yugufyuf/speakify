package com.example.speakify.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListeningHistoryRequest {
    String audioId;
    int lastPositionSeconds;
    int durationSeconds;
}
