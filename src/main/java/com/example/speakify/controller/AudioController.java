package com.example.speakify.controller;

import com.example.speakify.dto.request.AudioRequest;
import com.example.speakify.dto.request.AudioUpdateRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.dto.response.AudioResponse;
import com.example.speakify.service.AudioService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audio")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AudioController {
    AudioService audioService;

    @PostMapping
    ApiResponse<AudioResponse> create(@RequestBody @Valid AudioRequest audioRequest) {
        return ApiResponse.<AudioResponse>builder()
                .result(audioService.createAudio(audioRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<AudioResponse>> getAll() {
        return ApiResponse.<List<AudioResponse>>builder()
                .result(audioService.getAllAudios())
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<AudioResponse> getAudioById(@PathVariable String id) {
        return ApiResponse.<AudioResponse>builder()
                .result(audioService.getAudioById(id))
                .build();
    }

    @PutMapping("/{id}")
    ApiResponse<AudioResponse> updateAudioById(@PathVariable String id, @RequestBody @Valid AudioUpdateRequest request) {
        return ApiResponse.<AudioResponse>builder()
                .result(audioService.updateAudio(id, request))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> deleteAudioById(@PathVariable String id) {
        return ApiResponse.<String>builder()
                .result(audioService.deleteAudio(id))
                .build();
    }
}
