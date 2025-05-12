package com.example.speakify.controller;

import com.example.speakify.dto.request.SendEmailRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.service.MailService;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailController {
    final MailService service;

    @PostMapping("/send/otp")
    ApiResponse<Boolean> sendOTPForRegister(@RequestBody SendEmailRequest request) throws MessagingException {
        log.warn("controller");
        return ApiResponse.<Boolean>builder()
                .message("Send otp to: " + request.getEmail())
                .result(service.classifyBeforeSendEmail(request))
                .build();
    }

}
