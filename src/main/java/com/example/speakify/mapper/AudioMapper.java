package com.example.speakify.mapper;

import com.example.speakify.dto.request.AudioRequest;
import com.example.speakify.dto.response.AudioResponse;
import com.example.speakify.entity.Audio;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AudioMapper {
    Audio toAudio(AudioRequest audioRequest);
    AudioResponse toAudioResponse(Audio audio);
    List<AudioResponse> toAudioResponseList(List<Audio> audioList);
}
