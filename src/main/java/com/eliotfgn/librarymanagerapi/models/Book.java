package com.eliotfgn.librarymanagerapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    private Long id;
    private String title;
    private String author;
    private String collection;
    private int year;
    private int nbStock;
    private int nbFree;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Tag> tags;
    @OneToMany
    private List<Reservation> reservations;
}
