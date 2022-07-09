package com.eliotfgn.librarymanagerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestDto {
    private String title;
    private String author;
}
