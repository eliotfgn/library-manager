package com.eliotfgn.librarymanagerapi.repositories;

import com.eliotfgn.librarymanagerapi.models.Book;
import com.eliotfgn.librarymanagerapi.models.Reservation;
import com.eliotfgn.librarymanagerapi.models.ReservationStatus;
import com.eliotfgn.librarymanagerapi.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByBookAndAndStatus(Book book, ReservationStatus status);
    List<Reservation> findAllByUser(User user);
}