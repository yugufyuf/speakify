package com.example.speakify.controller;

import com.example.speakify.dto.request.BookRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.dto.response.BookResponse;
import com.example.speakify.service.BookService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookController {
    BookService bookService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/{publisherId}")
    ApiResponse<BookResponse> upload(@PathVariable String publisherId, @RequestBody BookRequest bookRequest) {
        return ApiResponse.<BookResponse>builder()
                .result(bookService.uploadBook(publisherId, bookRequest))
                .build();
    }

    //public
    @GetMapping()
    ApiResponse<List<BookResponse>> getAllBooks() {
        return ApiResponse.<List<BookResponse>>builder()
                .result(bookService.getAllBooks())
                .build();
    }

    //public
    @GetMapping("/{bookId}")
    ApiResponse<BookResponse> getBook(@PathVariable String bookId) {
        return ApiResponse.<BookResponse>builder()
                .result(bookService.getBook(bookId))
                .build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/{bookId}")
    ApiResponse<BookResponse> updateBook(@PathVariable String bookId, @RequestBody BookRequest bookRequest) {
        return ApiResponse.<BookResponse>builder()
                .result(bookService.updateBook(bookId, bookRequest))
                .build();
    }

    @Secured({"ADMIN", "USER"})
    @DeleteMapping("/{bookId}")
    ApiResponse<String> deleteBook(@PathVariable String bookId) {
        return ApiResponse.<String>builder()
                .result(bookService.deleteBook(bookId))
                .build();
    }
}
