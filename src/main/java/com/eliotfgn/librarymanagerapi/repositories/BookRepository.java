package com.eliotfgn.librarymanagerapi.repositories;

import com.eliotfgn.librarymanagerapi.models.Book;
import com.eliotfgn.librarymanagerapi.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByTagsContains(Tag tag);
}