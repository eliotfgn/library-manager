package com.eliotfgn.librarymanagerapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest implements Serializable {
    private String username;
    private Long bookId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startOn;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endOn;
}
