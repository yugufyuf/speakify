package com.example.speakify.repository;

import com.example.speakify.entity.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends JpaRepository<Audio, String> {
    int countByPublisher_Id(String publisherId);
}
