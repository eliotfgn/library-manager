package com.eliotfgn.librarymanagerapi.mappers;

import com.eliotfgn.librarymanagerapi.dto.BookDto;
import com.eliotfgn.librarymanagerapi.models.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {
    public BookDto mapToDto(Book book) {
        BookDto dto = BookDto
                .builder()
                .author(book.getAuthor())
                .title(book.getTitle())
                .collection(book.getCollection())
                .year(book.getYear())
                .nbStock(book.getNbStock())
                .nbFree(book.getNbFree())
                .description(book.getDescription())
                .id(book.getId())
                .build();
        List<String> tags = new ArrayList<>();
        book.getTags().forEach((tag) -> tags.add(tag.getLabel()));
        dto.setTags(tags);
        return dto;
    }

    public Book mapToBook(BookDto dto) {
        Book book = Book
                .builder()
                .author(dto.getAuthor())
                .title(dto.getTitle())
                .year(dto.getYear())
                .collection(dto.getCollection())
                .nbFree(dto.getNbStock())
                .nbStock(dto.getNbStock())
                .description(dto.getDescription())
                .build();

        return book;
    }
}
