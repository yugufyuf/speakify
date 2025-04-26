package com.example.speakify.controller;

import com.example.speakify.dto.request.AccountCreateRequest;
import com.example.speakify.dto.request.AccountUpdateRequest;
import com.example.speakify.dto.response.AccountResponse;
import com.example.speakify.entity.Account;
import com.example.speakify.dto.response.ApiResponse;
import com.example.speakify.service.AccountService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService accountService;

    @PostMapping()
    ApiResponse<Account> createAccount(@RequestBody @Valid AccountCreateRequest request) throws MessagingException {
        return ApiResponse.<Account>builder()
                .result(accountService.createAccount(request))
                .build();
    }

    @GetMapping()
    ApiResponse<List<Account>> getAccounts() {
        return ApiResponse.<List<Account>>builder()
                .result(accountService.getAccounts())
                .build();
    }

    @GetMapping("/{accountId}")
    ApiResponse<AccountResponse> getAccount(@PathVariable String accountId) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.getAccountById(accountId))
                .build();
    }

    @PutMapping("/{accountId}")
    ApiResponse<AccountResponse> updateAccount(@PathVariable String accountId,
                                  @RequestBody AccountUpdateRequest request) {
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.updateAccount(accountId, request))
                .build();
    }

    @DeleteMapping("/{accountId}")
    ApiResponse<String> deleteAccount(@PathVariable String accountId) {
        return ApiResponse.<String>builder()
                .result(accountService.deleteAccount(accountId))
                .build();
    }
}
