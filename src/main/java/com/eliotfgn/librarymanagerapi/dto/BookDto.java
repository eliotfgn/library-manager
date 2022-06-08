package com.eliotfgn.librarymanagerapi.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BookDto implements Serializable {
    private final Long id;
    private final String title;
    private final String author;
    private final String collection;
    private final int year;
    private final int nbStock;
    private final int nbFree;
    private final List<TagDto> tags;
}
