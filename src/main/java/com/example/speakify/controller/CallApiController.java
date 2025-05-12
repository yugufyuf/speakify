package com.example.speakify.controller;

import com.example.speakify.dto.request.BookRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.dto.response.ConvertResponse;
import com.example.speakify.service.CallApiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallApiController {
    CallApiService callApiService;

    @PostMapping()
    public ApiResponse<String> callApi(@RequestParam("file") MultipartFile file,
                                       @RequestParam("voiceType") String voiceType) {
        return ApiResponse.<String>builder()
                .result(callApiService.convertPdfToAudio(file, voiceType))
                .build();
    }
}
