package com.example.speakify.configuration;

import com.example.speakify.entity.Account;
import com.example.speakify.enums.Role;
import com.example.speakify.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    @NonFinal
    @Value("${app-init.admin-password}")
    protected String adminPassword;

    @NonFinal
    @Value("${app-init.admin-email}")
    protected String adminEmail;

    @NonFinal
    @Value("${app-init.admin-name}")
    protected String adminName;

    @Bean
    @Transactional
    ApplicationRunner applicationRunner(AccountRepository accountRepo) {
        return args -> {
            if(accountRepo.findByEmail(adminEmail).isEmpty()){
                Account account = Account.builder()
                        .email(adminEmail)
                        .password(passwordEncoder.encode(adminPassword))
                        .name(adminName)
                        .role(Role.ADMIN)
                        .build();
                accountRepo.save(account);
                log.warn("Admin account created with default password, please change it!");
            }
        };
    }
}