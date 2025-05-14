package com.example.speakify.repository;

import com.example.speakify.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, String> {
    Optional<List<ActivityLog>> findByAccount_Id(String account_Id);
}
