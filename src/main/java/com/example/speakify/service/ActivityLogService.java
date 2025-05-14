package com.example.speakify.service;

import com.example.speakify.dto.request.ActivityLogCreationRequest;
import com.example.speakify.dto.response.ActivityLogResponse;
import com.example.speakify.entity.Account;
import com.example.speakify.entity.ActivityLog;
import com.example.speakify.exception.AppException;
import com.example.speakify.exception.ErrorCode;
import com.example.speakify.mapper.ActivityLogMapper;
import com.example.speakify.repository.ActivityLogRepository;
import com.example.speakify.repository.AudioRepository;
import com.example.speakify.repository.BookRepository;
import com.example.speakify.repository.ConversionHistoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ActivityLogService {
    ActivityLogRepository activityLogRepository;
    AccountService accountService;
    ActivityLogMapper activityLogMapper;
    private final BookRepository bookRepository;
    private final AudioRepository audioRepository;
    private final ConversionHistoryRepository conversionHistoryRepository;

    public ActivityLogResponse create(ActivityLogCreationRequest request){
        Account account = accountService.getAccountFromAuthentication();
        ActivityLog activityLog = activityLogMapper.toActivityLog(request);
        activityLog.setAccount(account);
        return activityLogMapper.toActivityLogResponse(activityLogRepository.save(activityLog));
    }

    public List<ActivityLogResponse> getMyActivityLog(){
        Account account = accountService.getAccountFromAuthentication();
        List<ActivityLog> logs = activityLogRepository.findByAccount_Id(account.getId())
                .orElseThrow(() -> new AppException(ErrorCode.ACTIVITY_LOG_NOT_FOUND));
        return logs.stream().map(this::getActivityLog).toList();
    }

    public ActivityLogResponse getActivityLog(ActivityLog activityLog){
        Object targetDetail = null;
        switch (activityLog.getTargetType()){
            case BOOK -> targetDetail = bookRepository.findById(activityLog.getTargetId());
            case AUDIO -> targetDetail = audioRepository.findById(activityLog.getTargetId());
            case CONVERT_HISTORY -> targetDetail = conversionHistoryRepository.findById(activityLog.getTargetId());
            default -> throw new AppException(ErrorCode.ACTIVITY_LOG_NOT_FOUND);
        }

        return ActivityLogResponse.builder()
                .actionType(activityLog.getActionType())
                .targetId(activityLog.getTargetId())
                .targetType(activityLog.getTargetType())
                .targetDetails(targetDetail)
                .createdAt(activityLog.getCreatedAt())
                .description(activityLog.getDescription())
                .build();
    }
}
