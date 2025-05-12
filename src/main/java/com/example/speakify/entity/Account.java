package com.example.speakify.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import com.example.speakify.enums.Role;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String password;
    String email;
    Role role;
    LocalDate registerDate;

    @PrePersist
    protected void onCreate() {
        registerDate = LocalDate.now();
    }
}
