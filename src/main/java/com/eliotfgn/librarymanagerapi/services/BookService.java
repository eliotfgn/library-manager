package com.eliotfgn.librarymanagerapi.services;

import com.eliotfgn.librarymanagerapi.dto.BookDto;
import com.eliotfgn.librarymanagerapi.dto.TagRequest;
import com.eliotfgn.librarymanagerapi.mappers.BookMapper;
import com.eliotfgn.librarymanagerapi.models.Book;
import com.eliotfgn.librarymanagerapi.models.Reservation;
import com.eliotfgn.librarymanagerapi.models.Tag;
import com.eliotfgn.librarymanagerapi.repositories.BookRepository;
import com.eliotfgn.librarymanagerapi.repositories.TagRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final TagRepository tagRepository;
    private final BookMapper bookMapper;

    public void addBook(BookDto bookDto) {
        List<Reservation> reservations = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();
        Book book = Book.builder()
                .title(bookDto.getTitle())
                .author(bookDto.getAuthor())
                .collection(bookDto.getCollection())
                .year(bookDto.getYear())
                .nbStock(bookDto.getNbStock())
                .nbFree(bookDto.getNbStock())
                .reservations(reservations)
                .description(bookDto.getDescription())
                .tags(tags)
                .build();
        bookRepository.save(book);
    }

    public Book addTag(TagRequest tagRequest) {
        Optional<Tag> tag = tagRepository.findByLabel(tagRequest.getTag());
        Tag tag1;
        Book book = bookRepository.findById(tagRequest.getBookId()).orElseThrow(() ->
                new RuntimeException("Book not found"));

        tag1 = tag.orElseGet(() -> tagRepository.save(new Tag(tagRequest.getTag())));

        List<Tag> bookTags = book.getTags();
        bookTags.add(tag1);
        return book;
    }

    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public BookDto getBook(Long id) {
        return bookMapper.mapToDto(bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found")));
    }

    public Book getBookEntity(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<BookDto> getByTag(String tagLabel) {
        Tag tag = tagRepository.findByLabel(tagLabel)
                .orElseThrow(() -> new RuntimeException("No such tag stored."));

        return bookRepository.findAllByTagsContains(tag)
                .stream()
                .map(bookMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<String> getAllTags() {
        return tagRepository.findAll()
                .stream()
                .map(Tag::getLabel)
                .collect(Collectors.toList());
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
