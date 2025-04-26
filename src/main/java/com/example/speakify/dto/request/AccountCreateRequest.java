package com.example.speakify.dto.request;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data //co the thay the cho @Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreateRequest {
    @Size(min = 5, message = "NAME_INVALID")
    String name;

    @Size(min = 6, message = "PASSWORD_INVALID")
    String password;
    String email;
}
