package com.eliotfgn.librarymanagerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {
    private String username;
    private Long bookId;
    private Date startOn;
    private Date endOn;
}
