package com.example.speakify.controller;

import com.example.speakify.dto.request.AuthenticationRequest;
import com.example.speakify.dto.request.IntrospectRequest;
import com.example.speakify.dto.request.LogoutRequest;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.dto.response.AuthenticationResponse;
import com.example.speakify.dto.response.IntrospectResponse;
import com.example.speakify.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping
    public ApiResponse<AuthenticationResponse> generateToken(@RequestBody @Valid AuthenticationRequest request) {
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Boolean> logout(@RequestBody LogoutRequest request)
            throws JOSEException, ParseException {
        return ApiResponse.<Boolean>builder()
                .code(1000)
                .result(authenticationService.logout(request))
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody @Valid IntrospectRequest request)
            throws JOSEException, ParseException{
        return ApiResponse.<IntrospectResponse>builder()
                .result(authenticationService.introspect(request))
                .build();
    }
}
