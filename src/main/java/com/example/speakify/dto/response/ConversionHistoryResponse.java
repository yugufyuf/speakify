package com.example.speakify.dto.response;

import com.example.speakify.entity.Account;
import com.example.speakify.entity.Audio;
import com.example.speakify.enums.StatusConversion;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversionHistoryResponse {
    Account account;
    Audio audio;
    StatusConversion status;
    LocalDateTime createdAt;
}
