package uca.dss20192020.hotelproject.Interfaces;

import uca.dss20192020.hotelproject.Model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface IReservationService {
    List<Reservation> getReservationsWithinDates(Long hotelId, LocalDate firstDay, LocalDate lastDay);
    boolean ifHotelExists(Long hotelId);
}
