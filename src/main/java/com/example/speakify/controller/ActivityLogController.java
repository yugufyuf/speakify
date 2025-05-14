package com.example.speakify.controller;

import com.example.speakify.dto.request.ActivityLogCreationRequest;
import com.example.speakify.dto.response.ActivityLogResponse;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.service.ActivityLogService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity-log")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ActivityLogController {
    ActivityLogService activityLogService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping()
    public ApiResponse<ActivityLogResponse> create(@RequestBody @Valid ActivityLogCreationRequest request){
        return ApiResponse.<ActivityLogResponse>builder()
                .result(activityLogService.create(request))
                .build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping()
    public ApiResponse<List<ActivityLogResponse>> getMyActivityLog(){
        return ApiResponse.<List<ActivityLogResponse>>builder()
                .result(activityLogService.getMyActivityLog())
                .build();
    }
}
