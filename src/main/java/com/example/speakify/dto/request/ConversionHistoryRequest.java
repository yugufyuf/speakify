package com.example.speakify.dto.request;

import com.example.speakify.entity.Account;
import com.example.speakify.entity.Audio;
import com.example.speakify.enums.StatusConversion;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConversionHistoryRequest {
    Account account;
    Audio audio;
    StatusConversion statusConversion;
}
