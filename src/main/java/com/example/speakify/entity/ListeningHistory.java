package com.example.speakify.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ListeningHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    int lastPositionSeconds;
    int durationSeconds;

    LocalDateTime lastListenedAt;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "audio_id", referencedColumnName = "id")
    Audio audio;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    Account account;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        lastListenedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        lastListenedAt = LocalDateTime.now();
    }
}
