package uca.dss20192020.hotelproject.Interfaces;

import uca.dss20192020.hotelproject.Model.Room;

import java.util.List;

public interface ICheckInService {
    boolean checkIn(Long roomId, Long hotelId, String reservationCode);
    boolean checkOut(Long hotelId, String reservationCode);
    List<Room> getFreeRoomsForCheckIn(Long hotelId, String reservationCode);

}
