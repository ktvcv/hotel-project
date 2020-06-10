package dss.hotelproject.Interfaces;

import dss.hotelproject.Model.Reservation;

import java.time.LocalDate;
import java.util.List;


public interface IReservationService {
    List<Reservation> getReservationsWithinDates(Long hotelId, LocalDate firstDay, LocalDate lastDay);
}
