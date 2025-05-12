package com.example.speakify.service;

import com.example.speakify.dto.response.ConvertResponse;
import org.springframework.http.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CallApiService {
    private final RestTemplate restTemplate = new RestTemplate();

    // Gọi API FastAPI để chuyển PDF thành Audio
    public String convertPdfToAudio(MultipartFile file, String speaker) {
        String url = "http://127.0.0.1:8000/tts/convert";

        // Thiết lập headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // Tạo body với file và giọng nói
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("speaker", speaker);

        // Tạo HttpEntity với body và headers
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // Gửi yêu cầu POST tới FastAPI
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Trả về phản hồi từ API
        return response;
    }

    // Gọi API FastAPI để lấy thông tin về audio
    public String getAudioInfo() {
        String url = "http://127.0.0.1:8000/getaudio";

        // Gửi yêu cầu GET tới FastAPI
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);

        // Trả về phản hồi từ API
        return response.getBody();
    }
}
