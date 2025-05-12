package com.example.speakify.service;

import com.example.speakify.dto.request.AudioRequest;
import com.example.speakify.dto.request.BookRequest;
import com.example.speakify.dto.response.BookResponse;
import com.example.speakify.entity.Account;
import com.example.speakify.entity.Book;
import com.example.speakify.entity.Category;
import com.example.speakify.exception.AppException;
import com.example.speakify.exception.ErrorCode;
import com.example.speakify.mapper.BookMapper;
import com.example.speakify.mapper.CategoryMapper;
import com.example.speakify.repository.BookRepository;
import com.example.speakify.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookService {
    BookRepository bookRepository;
    BookMapper bookMapper;
    AccountService accountService;
    CategoryRepository categoryRepository;
    AudioService audioService;

    public BookResponse uploadBook(String categoryId, BookRequest request) {
        log.warn("book request {}", request);
        Account account = accountService.getAccount(accountService.getAccountFromAuthentication().getId());
        request.setAccount(account);
        Category category = categoryRepository.findById(categoryId)
                        .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXIST));
        request.setCategory(category);
        Book book = bookRepository.save(bookMapper.toBook(request));
        audioService.createAudio(AudioRequest.builder()
                        .title(request.getTitle())
                        .voiceType(request.getVoiceType())
                        .categoryId(categoryId)
                        .publisherId(account.getId())
                        .bookId(book.getId())
                .build());
        return bookMapper.toBookResponse(book);
    }

    public BookResponse updateBook(String bookId, BookRequest request) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new AppException(ErrorCode.DOC_NOT_EXIST));
        if(request.getTitle() != null) {
            book.setTitle(request.getTitle());
        }
        if(request.getAuthor() != null) {
            book.setAuthor(request.getAuthor());
        }
        if(request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }
        return bookMapper.toBookResponse(bookRepository.save(book));
    }

    public BookResponse getBook(String bookId) {
        return bookMapper.toBookResponse(bookRepository.findById(bookId)
                .orElseThrow(() -> new AppException(ErrorCode.DOC_NOT_EXIST)));
    }

    public List<BookResponse> getAllBooks() {
        return bookMapper.toBookResponseList(bookRepository.findAll());
    }

    public String deleteBook(String bookId) {
        bookRepository.deleteById(bookId);
        if (bookRepository.existsById(bookId)) {
            return "Deleted Failed";
        }
        return "Deleted Successfully";
    }
}
