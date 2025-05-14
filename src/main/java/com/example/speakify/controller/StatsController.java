package com.example.speakify.controller;

import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.service.StatsService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatsController {
    StatsService statsService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/upload-books")
    public ApiResponse<Integer> totalUploadBooks() {
        return ApiResponse.<Integer>builder()
                .result(statsService.totalUploadedBooks())
                .build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/convert-books")
    public ApiResponse<Integer> totalConvertBooks() {
        return ApiResponse.<Integer>builder()
                .result(statsService.totalConvertAudio())
                .build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/listening-books")
    public ApiResponse<Integer> totalListeningBooks() {
        return ApiResponse.<Integer>builder()
                .result(statsService.totalListeningHistory())
                .build();
    }
}
