package com.example.speakify.entity;

import com.example.speakify.enums.ActionType;
import com.example.speakify.enums.TargetType;
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
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Enumerated(EnumType.STRING)
    ActionType actionType;
    String targetId;
    @Enumerated(EnumType.STRING)
    TargetType targetType;

    @Column(columnDefinition = "TEXT")
    String description;

    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
