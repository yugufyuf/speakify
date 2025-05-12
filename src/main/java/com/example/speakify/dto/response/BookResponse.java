package com.example.speakify.dto.response;

import com.example.speakify.entity.Account;
import com.example.speakify.entity.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {
    String title;
    String author;
    LocalDate publishedDate;
    String description;
    Account publisher;
    Category category;
}
