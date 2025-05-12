package com.example.speakify.controller;

import com.example.speakify.dto.request.ConversionHistoryRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.dto.response.ConversionHistoryResponse;
import com.example.speakify.service.ConversionHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConversionHistoryController {
    ConversionHistoryService conversionHistoryService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    ApiResponse<ConversionHistoryResponse> create(ConversionHistoryRequest conversionHistoryRequest) {
        return ApiResponse.<ConversionHistoryResponse>builder()
                .result(conversionHistoryService.create(conversionHistoryRequest))
                .build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{id}")
    ApiResponse<ConversionHistoryResponse> getById(@PathVariable String id) {
        return ApiResponse.<ConversionHistoryResponse>builder()
                .result(conversionHistoryService.findById(id))
                .build();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    ApiResponse<List<ConversionHistoryResponse>> getAll() {
        return ApiResponse.<List<ConversionHistoryResponse>>builder()
                .result(conversionHistoryService.findAll())
                .build();
    }

    @Secured({"ADMIN", "USER"})
    @GetMapping("/account/{accountId}")
    ApiResponse<List<ConversionHistoryResponse>> getByAccountId(@PathVariable String accountId) {
        return ApiResponse.<List<ConversionHistoryResponse>>builder()
                .result(conversionHistoryService.findByAccountId(accountId))
                .build();
    }

    @Secured({"ADMIN", "USER"})
    @DeleteMapping("/{id}")
    ApiResponse<String> delete(@PathVariable String id) {
        return ApiResponse.<String>builder()
                .result(conversionHistoryService.deleteById(id))
                .build();
    }

    @GetMapping("/total-convert-book")
    @PreAuthorize("hasAuthority('USER')")
    ApiResponse<Long> getTotalConverBook() {
        return ApiResponse.<Long>builder()
                .result(conversionHistoryService.totalConvertHistory())
                .build();
    }
}
