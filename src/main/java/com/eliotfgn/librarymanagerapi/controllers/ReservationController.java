package com.eliotfgn.librarymanagerapi.controllers;

import com.eliotfgn.librarymanagerapi.dto.ReservationDto;
import com.eliotfgn.librarymanagerapi.dto.ReservationRequest;
import com.eliotfgn.librarymanagerapi.models.Reservation;
import com.eliotfgn.librarymanagerapi.services.ReservationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;

    @PostMapping("/new")
    public ResponseEntity<Reservation> newReservation(@RequestBody ReservationRequest reservationRequest) {
        return ResponseEntity.ok().body(reservationService.reserve(reservationRequest));
    }

    @GetMapping("/for/{username}")
    public ResponseEntity<List<ReservationDto>> getForUser(@PathVariable String username) {
        return ResponseEntity.ok().body(reservationService.allForUser(username));
    }
}
