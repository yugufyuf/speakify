package com.example.speakify.dto.response;

import com.example.speakify.entity.Account;
import com.example.speakify.entity.Book;
import com.example.speakify.entity.Category;
import com.example.speakify.enums.VoiceType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioResponse {
    String title;
    String path;
    float duration;
    VoiceType voiceType;
    Category category;
    Account publisher;
    Book book;
    LocalDateTime updateAt;
    LocalDateTime createAt;
}
