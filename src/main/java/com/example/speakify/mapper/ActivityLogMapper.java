package com.example.speakify.mapper;

import com.example.speakify.dto.request.ActivityLogCreationRequest;
import com.example.speakify.dto.response.ActivityLogResponse;
import com.example.speakify.entity.ActivityLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ActivityLogMapper {
    ActivityLog toActivityLog(ActivityLogCreationRequest activityLog);
    ActivityLogResponse toActivityLogResponse(ActivityLog activityLog);
    List<ActivityLogResponse> toActivityLogResponse(List<ActivityLog> activityLogs);
}
