package com.example.speakify.service;

import com.example.speakify.dto.request.AccountCreateRequest;
import com.example.speakify.dto.request.AccountUpdateRequest;
import com.example.speakify.dto.request.SendEmailRequest;
import com.example.speakify.dto.response.AccountResponse;
import com.example.speakify.entity.Account;
import com.example.speakify.enums.EmailType;
import com.example.speakify.enums.Role;
import com.example.speakify.exception.AppException;
import com.example.speakify.exception.ErrorCode;
import com.example.speakify.mapper.AccountMapper;
import com.example.speakify.repository.AccountRepository;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {
    AccountRepository accountRepository;
    AccountMapper accountMapper;

    PasswordEncoder passwordEncoder;
    MailService mailService;

    public Account createAccount(AccountCreateRequest request) throws MessagingException {
        if(accountRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);

        Account account = Account.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .role(Role.USER)
                .build();

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .emailType(EmailType.REGISTER)
                .email(request.getEmail())
                .build();
        mailService.classifyBeforeSendEmail(sendEmailRequest);

        return accountRepository.save(account);
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public AccountResponse getMyAccount(){
        return accountMapper.toAccountResponse(getAccountFromAuthentication());
    }

    public AccountResponse getAccountById(String id) {
        return accountMapper.toAccountResponse(accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public Account getAccount(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public AccountResponse updateAccount(String id, AccountUpdateRequest request) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if (request.getName() != null) {
            account.setName(request.getName());
        }
        if (request.getPassword() != null) {
            account.setPassword(request.getPassword());
        }
        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    public String deleteAccount(String id) {
        accountRepository.deleteById(id);
        if(accountRepository.existsById(id)) {
            return "Delete failed";
        }
        return "Delete Success";
    }

    public Account getAccountFromAuthentication(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountRepository.findByEmail(email).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }
}