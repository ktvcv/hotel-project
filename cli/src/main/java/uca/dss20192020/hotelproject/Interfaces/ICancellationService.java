package uca.dss20192020.hotelproject.Interfaces;

import uca.dss20192020.hotelproject.Model.Reservation;

public interface ICancellationService {
    double cancelReservation(Long hotelId, Reservation reservation);
}
