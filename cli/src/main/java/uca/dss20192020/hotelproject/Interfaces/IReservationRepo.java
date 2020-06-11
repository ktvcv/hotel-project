package uca.dss20192020.hotelproject.Interfaces;

import uca.dss20192020.hotelproject.Enum.Services;
import uca.dss20192020.hotelproject.Model.Reservation;

import java.util.List;

public interface IReservationRepo {
    Reservation getReservationByReservationCode(Long hotelId, String reservationCode);
    boolean removeReservation(Long hotelId, String reservationCode);
    Reservation getReservationById(Long hotelId, Long reservationId);
    void saveChangedReservation(Long hotelId, Reservation reservation);
    Reservation saveNewReservation(Long hotelId, Reservation reservation);
    List<Services> getAllServicesList(Long hotelId, String reservationCode);
    List<Reservation> getAllReservation(Long hotelId);
}
