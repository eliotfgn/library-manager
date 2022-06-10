package com.eliotfgn.librarymanagerapi.exceptions;

public class BadReservationPeriodException extends RuntimeException{
    public BadReservationPeriodException(String message) {
        super(message);
    }
}
