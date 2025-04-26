package com.example.speakify.service;

import com.example.speakify.dto.request.AudioRequest;
import com.example.speakify.dto.request.AudioUpdateRequest;
import com.example.speakify.dto.request.ConversionHistoryRequest;
import com.example.speakify.dto.response.AudioResponse;
import com.example.speakify.entity.Account;
import com.example.speakify.entity.Audio;
import com.example.speakify.entity.Book;
import com.example.speakify.entity.Category;
import com.example.speakify.enums.StatusConversion;
import com.example.speakify.enums.VoiceType;
import com.example.speakify.exception.AppException;
import com.example.speakify.exception.ErrorCode;
import com.example.speakify.mapper.AudioMapper;
import com.example.speakify.repository.AccountRepository;
import com.example.speakify.repository.AudioRepository;
import com.example.speakify.repository.BookRepository;
import com.example.speakify.repository.CategoryRepository;
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
public class AudioService {
    AudioMapper audioMapper;
    AudioRepository audioRepository;
    BookRepository bookRepository;
    CategoryRepository categoryRepository;
    AccountRepository accountRepository;
    ConversionHistoryService conversionHistoryService;

    public AudioResponse createAudio(AudioRequest audioRequest) {
        Book book = bookRepository.findById(audioRequest.getBookId())
                .orElseThrow(() -> new AppException(ErrorCode.DOC_NOT_EXIST));
        Category category = categoryRepository.findById(audioRequest.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
        Account account = accountRepository.findById(audioRequest.getPublisherId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        Audio audio = Audio.builder()
                .title(audioRequest.getTitle())
                .path("sfjkhf")
                .voiceType(VoiceType.valueOf(audioRequest.getVoiceType()))
                .book(book)
                .category(category)
                .duration(2)
                .publisher(account)
                .build();
        audioRepository.save(audio);
        conversionHistoryService.create(ConversionHistoryRequest.builder()
                .audio(audio)
                .account(account)
                .statusConversion(StatusConversion.FINISHED)
                .build());
        return audioMapper.toAudioResponse(audio);
    }

    public List<AudioResponse> getAllAudios() {
        return audioMapper.toAudioResponseList(audioRepository.findAll());
    }

    public AudioResponse getAudioById(String id) {
        return audioMapper.toAudioResponse(audioRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.AUDIO_NOT_EXIST)));
    }

    public AudioResponse updateAudio(String id, AudioUpdateRequest request) {
        Audio audio = audioRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.AUDIO_NOT_EXIST));
        if(request.getTitle() != null){
            audio.setTitle(request.getTitle());
        }
        if(request.getCategory() != null){
            audio.setCategory(request.getCategory());
        }
        return audioMapper.toAudioResponse(audioRepository.save(audio));
    }

    public String deleteAudio(String id) {
        audioRepository.deleteById(id);
        if(audioRepository.existsById(id)){
            return "Audio deleted failed";
        }
        return "Audio deleted successfully";
    }
}
