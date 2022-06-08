package com.eliotfgn.librarymanagerapi.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TagDto implements Serializable {
    private final Long id;
    private final String label;
}
