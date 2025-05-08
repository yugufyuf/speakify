package com.example.speakify.dto.request;

import com.example.speakify.exception.ErrorCode;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationRequest {
    @NotBlank(message = "NOT_NULL")
    String email;
    @NotBlank(message = "NOT_NULL")
    String password;
}
