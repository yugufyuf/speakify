package com.example.speakify.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioRequest {
    @NotBlank(message = "NOT_NULL")
    String title;
    @NotBlank(message = "NOT_NULL")
    String voiceType;
    @NotBlank(message = "NOT_NULL")
    String categoryId;
    @NotBlank(message = "NOT_NULL")
    String publisherId;
    @NotBlank(message = "NOT_NULL")
    String bookId;
}
