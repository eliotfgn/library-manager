package com.eliotfgn.librarymanagerapi.repositories;

import com.eliotfgn.librarymanagerapi.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}