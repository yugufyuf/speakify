package com.example.speakify.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookByCategoryResponse {
    String categoryId;
    String categoryName;
    List<BookResponse> books;
}
