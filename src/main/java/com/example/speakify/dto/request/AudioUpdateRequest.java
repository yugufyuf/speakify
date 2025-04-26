package com.example.speakify.dto.request;

import com.example.speakify.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioUpdateRequest {
    String title;
    Category category;
}
