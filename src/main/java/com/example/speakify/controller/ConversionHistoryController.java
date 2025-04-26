package com.example.speakify.controller;

import com.example.speakify.dto.request.ConversionHistoryRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.dto.response.ConversionHistoryResponse;
import com.example.speakify.service.ConversionHistoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ConversionHistoryController {
    ConversionHistoryService conversionHistoryService;

    @PostMapping
    ApiResponse<ConversionHistoryResponse> create(ConversionHistoryRequest conversionHistoryRequest) {
        return ApiResponse.<ConversionHistoryResponse>builder()
                .result(conversionHistoryService.create(conversionHistoryRequest))
                .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ConversionHistoryResponse> getById(@PathVariable String id) {
        return ApiResponse.<ConversionHistoryResponse>builder()
                .result(conversionHistoryService.findById(id))
                .build();
    }

    @GetMapping
    ApiResponse<List<ConversionHistoryResponse>> getAll() {
        return ApiResponse.<List<ConversionHistoryResponse>>builder()
                .result(conversionHistoryService.findAll())
                .build();
    }

    @GetMapping("/account/{accountId}")
    ApiResponse<List<ConversionHistoryResponse>> getByAccountId(@PathVariable String accountId) {
        return ApiResponse.<List<ConversionHistoryResponse>>builder()
                .result(conversionHistoryService.findByAccountId(accountId))
                .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<String> delete(@PathVariable String id) {
        return ApiResponse.<String>builder()
                .result(conversionHistoryService.deleteById(id))
                .build();
    }
}
