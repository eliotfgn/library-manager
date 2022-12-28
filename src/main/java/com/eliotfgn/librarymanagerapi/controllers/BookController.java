package com.eliotfgn.librarymanagerapi.controllers;

import com.eliotfgn.librarymanagerapi.dto.BookDto;
import com.eliotfgn.librarymanagerapi.dto.TagRequest;
import com.eliotfgn.librarymanagerapi.models.Book;
import com.eliotfgn.librarymanagerapi.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    //
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody BookDto bookDto) {
        bookService.addBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("New book added!");
    }

    @PostMapping("add/tag")
    public ResponseEntity<Book> addTag(@RequestBody TagRequest tagRequest) {
        return ResponseEntity.ok().body(bookService.addTag(tagRequest));
    }

    @GetMapping("/tags")
    public ResponseEntity<List<String>> getAllTags() {
        return ResponseEntity.ok().body(bookService.getAllTags());
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookDto>> catalog() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) {
        return ResponseEntity.ok().body(bookService.getBook(bookId));
    }

    @GetMapping("/by-tag/{tag}")
    public ResponseEntity<List<BookDto>> getBooksByTag(@PathVariable String tag) {
        return ResponseEntity.ok().body(bookService.getByTag(tag));
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok().body("Book deleted!");
    }
}
