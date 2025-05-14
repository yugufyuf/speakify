package com.example.speakify.dto.response;

import com.example.speakify.entity.Account;
import com.example.speakify.entity.Audio;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListeningHistoryResponse {
    int lastPositionSeconds;
    int durationSeconds;

    LocalDateTime lastListenedAt;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    Audio audio;
    Account account;
}
