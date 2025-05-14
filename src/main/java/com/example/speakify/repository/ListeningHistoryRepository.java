package com.example.speakify.repository;

import com.example.speakify.entity.Account;
import com.example.speakify.entity.ListeningHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListeningHistoryRepository extends JpaRepository<ListeningHistory, String> {
    List<ListeningHistory> findByAccount(Account account);
    int countByAccount_Id(String id);
}
