package com.example.speakify.service;

import com.example.speakify.dto.request.BookRequest;
import com.example.speakify.dto.response.BookResponse;
import com.example.speakify.entity.Book;
import com.example.speakify.exception.AppException;
import com.example.speakify.exception.ErrorCode;
import com.example.speakify.mapper.BookMapper;
import com.example.speakify.repository.BookRepository;
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

    public BookResponse uploadBook(String publisherId, BookRequest request) {
        request.setAccount(accountService.getAccount(publisherId));
        return bookMapper.toBookResponse(bookRepository.save(bookMapper.toBook(request)));
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
