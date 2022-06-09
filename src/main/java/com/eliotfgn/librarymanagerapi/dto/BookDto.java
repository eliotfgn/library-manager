package com.eliotfgn.librarymanagerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto implements Serializable {
    private String title;
    private String author;
    private String collection;
    private int year;
    private int nbStock;
    private int nbFree;
    private List<String> tags;
}
