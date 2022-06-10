package com.eliotfgn.librarymanagerapi.mappers;

import com.eliotfgn.librarymanagerapi.dto.ReservationDto;
import com.eliotfgn.librarymanagerapi.dto.ReservationRequest;
import com.eliotfgn.librarymanagerapi.models.Book;
import com.eliotfgn.librarymanagerapi.models.Reservation;
import com.eliotfgn.librarymanagerapi.models.User;
import org.springframework.stereotype.Component;

@Component
public class ReservationMapper {
    public ReservationDto mapToDto(Reservation reservation, User user, Book book) {
        return ReservationDto
                .builder()
                .username(user.getUsername())
                .bookTitle(book.getTitle())
                .endOn(reservation.getEndDate())
                .startOn(reservation.getStartDate())
                .status(reservation.getStatus())
                .build();
    }
}
