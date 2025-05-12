package com.example.speakify.repository;

import com.example.speakify.entity.ConversionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, String> {
    Optional<List<ConversionHistory>> findAllByAccount_Id(String accountId);
    long countByAccount_Id(String accountId);
}
