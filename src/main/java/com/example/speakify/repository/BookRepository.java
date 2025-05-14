package com.example.speakify.repository;

import com.example.speakify.dto.response.BookResponse;
import com.example.speakify.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    Optional<BookResponse> findByCategory_MainCategory(String category);
    List<BookResponse> findAllByCategory_Id(String categoryId);
    int countByPublisher_Id(String publisherId);
}
