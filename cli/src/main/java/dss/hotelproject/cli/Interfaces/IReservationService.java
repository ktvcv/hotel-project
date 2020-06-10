package dss.hotelproject.cli.Interfaces;



import dss.hotelproject.cli.Model.Reservation;

import java.time.LocalDate;
import java.util.List;


public interface IReservationService {
    List<Reservation> getReservationsWithinDates(Long hotelId, LocalDate firstDay, LocalDate lastDay);
    Reservation getReservationByCodeAndHotel(Long hotelId, String code);
    void saveReservation(Reservation reservation);
    List<Reservation> getAllByHotelId(Long id);
}
