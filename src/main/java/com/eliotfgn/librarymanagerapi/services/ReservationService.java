package com.eliotfgn.librarymanagerapi.services;

import com.eliotfgn.librarymanagerapi.dto.ReservationDto;
import com.eliotfgn.librarymanagerapi.dto.ReservationRequest;
import com.eliotfgn.librarymanagerapi.exceptions.BadReservationPeriodException;
import com.eliotfgn.librarymanagerapi.mappers.ReservationMapper;
import com.eliotfgn.librarymanagerapi.models.Book;
import com.eliotfgn.librarymanagerapi.models.Reservation;
import com.eliotfgn.librarymanagerapi.models.ReservationStatus;
import com.eliotfgn.librarymanagerapi.models.User;
import com.eliotfgn.librarymanagerapi.repositories.BookRepository;
import com.eliotfgn.librarymanagerapi.repositories.ReservationRepository;
import com.eliotfgn.librarymanagerapi.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.eliotfgn.librarymanagerapi.models.ReservationStatus.NOT_STARTED;
import static com.eliotfgn.librarymanagerapi.models.ReservationStatus.ONGOING;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ReservationMapper reservationMapper;

    public Reservation reserve(ReservationRequest reservationRequest) {
        Book book = bookRepository.findById(reservationRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder
                .getContext().getAuthentication()
                .getPrincipal()).getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Reservation reservation;
        Date reservationCreatedDate;

        if (user.getCanReserve()) {
            if (reservationRequest.getStartOn().before(new Date()) ||
                    reservationRequest.getEndOn().before(new Date())) {
                log.error("Start: "+ reservationRequest.getStartOn());
                throw new BadReservationPeriodException("Your reservation should start before and " +
                        "end after the current date.");
            } else if (reservationRequest.getStartOn().after(reservationRequest.getEndOn())) {
                throw new BadReservationPeriodException("Your end date should be after the start date");
            }
            else {
                List<Reservation> notStartedReservation = reservationRepository
                        .findAllByBookAndAndStatus(book, NOT_STARTED);
                long overlaps;
                if (book.getNbFree()>0) {
                    if (notStartedReservation.size()>0) {
                        overlaps = overlappingReservations(reservationRequest, notStartedReservation);
                        if (book.getNbFree() - overlaps > 0) {
                            //the user can reserve for a period and his reservation wouldn't affect on past not started reservations
                            reservation = newReservation(reservationRequest, user, book);
                            reservationCreatedDate = new Date();
                        } else {
                            throw new BadReservationPeriodException("There are no free items during the period of your reservation.");
                        }
                    } else {
                        reservation = newReservation(reservationRequest, user, book);
                        reservationCreatedDate = new Date();
                    }
                } else {
                    log.info("Free items: "+ book.getNbFree());
                    throw new BadReservationPeriodException("All items have been reserved.");
                }

            }
            //set the reservation status
            if (reservation.getStartDate().after(reservationCreatedDate))
                reservation.setStatus(NOT_STARTED);
            else
                reservation.setStatus(ONGOING);
        } else {
            throw new RuntimeException("User can't reserve");
        }

        book.getReservations().add(reservation);
        // TODO: 10/06/2022
        //  I should add a system to schedule the end of the reservation

        return reservationRepository.save(reservation);
    }

    private Reservation newReservation(ReservationRequest reservationRequest, User user, Book book) {
        Reservation reservation = Reservation
                .builder()
                .user(user)
                .book(book)
                .startDate(reservationRequest.getStartOn())
                .endDate(reservationRequest.getEndOn())
                .build();
        book.setNbFree(book.getNbFree()-1);
        return reservation;
    }

    private long overlappingReservations(ReservationRequest request,
                                        List<Reservation> notStartedReservations) {

        return notStartedReservations.stream()
                .filter((reservation) -> !((request.getStartOn().before(reservation.getStartDate())
                        && request.getEndOn().before(reservation.getStartDate()))
                        || (request.getStartOn().after(reservation.getEndDate())
                        && request.getEndOn().after(reservation.getEndDate()))))
                .count();
    }

    public List<ReservationDto> allForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return reservationRepository.findAllByUser(user)
                .stream()
                .map(reservation -> reservationMapper.mapToDto(reservation, user, reservation.getBook()))
                .collect(Collectors.toList());
    }
}
