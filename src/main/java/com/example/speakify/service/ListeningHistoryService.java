package com.example.speakify.service;

import com.example.speakify.dto.request.ListeningHistoryRequest;
import com.example.speakify.dto.response.ListeningHistoryResponse;
import com.example.speakify.entity.Account;
import com.example.speakify.entity.Audio;
import com.example.speakify.entity.ListeningHistory;
import com.example.speakify.repository.AudioRepository;
import com.example.speakify.repository.ListeningHistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ListeningHistoryService {

    AudioRepository audioRepository;
    ListeningHistoryRepository listeningHistoryRepository;
    private final AccountService accountService;

    public ListeningHistory saveListeningHistory(ListeningHistoryRequest request) {
        Account account = accountService.getAccountFromAuthentication();
        Audio audio = audioRepository.findById(request.getAudioId())
                .orElseThrow(() -> new RuntimeException("Audio không tồn tại"));

        ListeningHistory history = ListeningHistory.builder()
                .account(account)
                .audio(audio)
                .lastPositionSeconds(request.getLastPositionSeconds())
                .durationSeconds(request.getDurationSeconds())
                .build();

        return listeningHistoryRepository.save(history);
    }

    public List<ListeningHistory> getHistoryByAccount() {
        Account account = accountService.getAccountFromAuthentication();
        return listeningHistoryRepository.findByAccount(account);
    }
}
