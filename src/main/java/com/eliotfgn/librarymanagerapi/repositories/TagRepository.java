package com.eliotfgn.librarymanagerapi.repositories;

import com.eliotfgn.librarymanagerapi.models.Book;
import com.eliotfgn.librarymanagerapi.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByLabel(String label);
}