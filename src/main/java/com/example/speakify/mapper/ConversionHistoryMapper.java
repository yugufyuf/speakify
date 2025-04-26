package com.example.speakify.mapper;

import com.example.speakify.dto.request.ConversionHistoryRequest;
import com.example.speakify.dto.response.ConversionHistoryResponse;
import com.example.speakify.entity.ConversionHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversionHistoryMapper {
    ConversionHistory toConversionHistory(ConversionHistoryRequest conversionHistoryRequest);
    ConversionHistoryResponse toConversionHistoryResponse(ConversionHistory conversionHistory);
    List<ConversionHistoryResponse> toConversionHistoryResponse(List<ConversionHistory> conversionHistories);
}
