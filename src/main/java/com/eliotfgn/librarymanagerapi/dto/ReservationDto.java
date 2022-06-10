package com.eliotfgn.librarymanagerapi.dto;

import com.eliotfgn.librarymanagerapi.models.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationDto {
    private String username;
    private String bookTitle;
    private Date startOn;
    private Date endOn;
    private ReservationStatus status;
}
