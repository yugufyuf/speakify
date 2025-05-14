package com.example.speakify.service;

import com.example.speakify.entity.Account;
import com.example.speakify.repository.AudioRepository;
import com.example.speakify.repository.BookRepository;
import com.example.speakify.repository.ListeningHistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StatsService {
    AccountService accountService;
    BookRepository bookRepository;
    AudioRepository audioRepository;
    ListeningHistoryRepository listeningHistoryRepository;

    public int totalUploadedBooks() {
        Account account = accountService.getAccountFromAuthentication();
        return bookRepository.countByPublisher_Id(account.getId());
    }

    public int totalConvertAudio(){
        Account account = accountService.getAccountFromAuthentication();
        return audioRepository.countByPublisher_Id(account.getId());
    }

    public int totalListeningHistory(){
        Account account = accountService.getAccountFromAuthentication();
        return listeningHistoryRepository.countByAccount_Id(account.getId());
    }
}
