package com.example.speakify.controller;

import com.example.speakify.dto.request.SendEmailRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.service.MailSenderService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    MailSenderService mailService;

    //public
    @PostMapping()
    ResponseEntity<String> createAccount(@RequestBody @Valid SendEmailRequest request) {
        //String content = "Dear Thu 7, 2/11/2024 Pham Huynh Anh Thu 22H1120026 25/12/2004";
        mailService.sendMail(request.getEmail(), request.getSubject(), request.getBody());
        return ResponseEntity.ok().build();
    }
}
