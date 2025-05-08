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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/audio")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AudioController {
    AudioService audioService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    ApiResponse<AudioResponse> create(@RequestBody @Valid AudioRequest audioRequest) {
        return ApiResponse.<AudioResponse>builder()
                .result(audioService.createAudio(audioRequest))
                .build();
    }

    //public
    @GetMapping()
    ApiResponse<List<AudioResponse>> getAll() {
        return ApiResponse.<List<AudioResponse>>builder()
                .result(audioService.getAllAudios())
                .build();
    }

    @Secured({"ADMIN", "USER"})
    @GetMapping("/{id}")
    ApiResponse<AudioResponse> getAudioById(@PathVariable String id) {
        return ApiResponse.<AudioResponse>builder()
                .result(audioService.getAudioById(id))
                .build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/{id}")
    ApiResponse<AudioResponse> updateAudioById(@PathVariable String id, @RequestBody @Valid AudioUpdateRequest request) {
        return ApiResponse.<AudioResponse>builder()
                .result(audioService.updateAudio(id, request))
                .build();
    }

    @Secured({"ADMIN", "USER"})
    @DeleteMapping("/{id}")
    ApiResponse<String> deleteAudioById(@PathVariable String id) {
        return ApiResponse.<String>builder()
                .result(audioService.deleteAudio(id))
                .build();
    }
}
