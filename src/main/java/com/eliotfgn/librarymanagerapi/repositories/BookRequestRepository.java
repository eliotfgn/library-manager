package com.eliotfgn.librarymanagerapi.repositories;

import com.eliotfgn.librarymanagerapi.models.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {
}