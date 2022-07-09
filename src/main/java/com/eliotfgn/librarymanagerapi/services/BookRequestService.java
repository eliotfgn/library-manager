package com.eliotfgn.librarymanagerapi.services;

import com.eliotfgn.librarymanagerapi.dto.RequestDto;
import com.eliotfgn.librarymanagerapi.models.BookRequest;
import com.eliotfgn.librarymanagerapi.repositories.BookRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class BookRequestService {
    private BookRequestRepository repository;

    public List<BookRequest> getAllRequest() {
        return repository.findAll();
    }

    public void newRequest(RequestDto dto) {
        BookRequest request = BookRequest.builder()
                .author(dto.getAuthor())
                .title(dto.getTitle())
                .build();
        repository.save(request);
    }
}
