package dss.hotelproject.Interfaces;

import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.util.Triple;

import java.time.LocalDate;
import java.util.List;


public interface ISearchForFreeRooms {
    List<Triple> getAllRoomInHotelForNumberOfGuests(Long hotelId, int numberOfPersons);
    List<Triple> findFreeTypeRoomsForPeriod(Long hotelId, LocalDate firstDay, LocalDate lastDay, int numberOfPersons, List<Reservation> reservations);
    List<Room> getFreeRoomsForCheckIn(Long hotelId, Reservation reservation);

}
