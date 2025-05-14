package com.example.speakify.controller;

import com.example.speakify.dto.request.ListeningHistoryRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.entity.ListeningHistory;
import com.example.speakify.service.ListeningHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listening-history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ListeningHistoryController {
    ListeningHistoryService listeningHistoryService;

    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ApiResponse<ListeningHistory> saveListeningHistory(@RequestBody ListeningHistoryRequest request) {
        return ApiResponse.<ListeningHistory>builder()
                .result(listeningHistoryService.saveListeningHistory(request))
                .build();
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ApiResponse<List<ListeningHistory>> getListeningHistory() {
        return ApiResponse.<List<ListeningHistory>>builder()
                .result(listeningHistoryService.getHistoryByAccount())
                .build();
    }
}
