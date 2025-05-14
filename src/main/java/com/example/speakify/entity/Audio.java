package com.example.speakify.entity;

import com.example.speakify.enums.VoiceType;
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
public class Audio {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String title;
    String path;
    float duration;

    @Enumerated(EnumType.STRING)
    VoiceType voiceType;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    Account publisher;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    Book book;

    LocalDateTime updateAt;
    LocalDateTime createAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateAt = LocalDateTime.now();
    }
}
