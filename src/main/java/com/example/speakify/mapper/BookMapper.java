package com.example.speakify.mapper;

import com.example.speakify.dto.request.BookRequest;
import com.example.speakify.dto.response.BookResponse;
import com.example.speakify.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    // Chỉ định account map vào publisher
    //BookRequest có Account account, nhưng trong BookMapper.toBook(bookRequest), có thể nó không được ánh xạ
    // tự động sang publisher của Book.
    //Điều này xảy ra nếu BookMapper không nhận diện đúng field account để map vào publisher.
    @Mapping(source = "account", target = "publisher")
    Book toBook(BookRequest bookRequest);
    BookResponse toBookResponse(Book book);
    List<BookResponse> toBookResponseList(List<Book> books);
}
