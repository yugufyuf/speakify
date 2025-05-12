package com.example.speakify.controller;

import com.example.speakify.dto.request.ValidationOTPRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.service.OTPService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OTPController {
    OTPService otpService;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/validation")
    public ApiResponse<Boolean> validateOTP(@RequestBody @Valid ValidationOTPRequest request) {
        return ApiResponse.<Boolean>builder()
                .result(otpService.validateSecureOTP(request))
                .build();
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping()
    public ApiResponse<String> generateOTP(@RequestBody ValidationOTPRequest request) {
        return ApiResponse.<String>builder()
                .result(otpService.generateSecureOTP(request.getEmail()))
                .build();
    }
}
