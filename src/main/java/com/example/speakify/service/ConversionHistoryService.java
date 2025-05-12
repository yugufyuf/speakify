package com.example.speakify.service;

import com.example.speakify.dto.request.ConversionHistoryRequest;
import com.example.speakify.dto.response.ConversionHistoryResponse;
import com.example.speakify.entity.Account;
import com.example.speakify.entity.ConversionHistory;
import com.example.speakify.exception.AppException;
import com.example.speakify.exception.ErrorCode;
import com.example.speakify.mapper.ConversionHistoryMapper;
import com.example.speakify.repository.ConversionHistoryRepository;
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
public class ConversionHistoryService {
    ConversionHistoryRepository repository;
    ConversionHistoryMapper mapper;
    AccountService accountService;
    private final ConversionHistoryRepository conversionHistoryRepository;

    public ConversionHistoryResponse create(ConversionHistoryRequest request) {
        log.warn("convert: {}", request);
        ConversionHistory conversionHistory = mapper.toConversionHistory(request);
        return mapper.toConversionHistoryResponse(repository.save(conversionHistory));
    }

    public ConversionHistoryResponse findById(String id) {
        return mapper.toConversionHistoryResponse(repository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.HISTORY_NOT_EXIST)));
    }

    public List<ConversionHistoryResponse> findAll() {
        return mapper.toConversionHistoryResponse(repository.findAll());
    }

    public List<ConversionHistoryResponse> getAllMyConvertedAudio(){
        Account account = accountService.getAccountFromAuthentication();
        List<ConversionHistory> conversionHistories = conversionHistoryRepository.findAllByAccount_Id(account.getId())
                .orElseThrow(() -> new AppException(ErrorCode.HISTORY_NOT_EXIST));
        return mapper.toConversionHistoryResponse(conversionHistories);
    }

    public List<ConversionHistoryResponse> findByAccountId(String accountId) {
        return mapper.toConversionHistoryResponse(repository.findAllByAccount_Id(accountId)
                .orElseThrow(() -> new AppException(ErrorCode.HISTORY_NOT_EXIST)));
    }

    public String deleteById(String id) {
        repository.deleteById(id);
        if(repository.existsById(id))
            return "Delete failed";
        return "Delete successful";
    }

    public long totalConvertHistory() {
        Account account = accountService.getAccountFromAuthentication();
        return repository.countByAccount_Id(account.getId());
    }
}
