package uca.dss20192020.hotelproject.Interfaces;

import uca.dss20192020.hotelproject.util.Triple;

import java.time.LocalDate;
import java.util.List;

public interface ISearchForFreeRooms {
    List<Triple> findFreeTypeRoomsForPeriod(Long hotelId, LocalDate firstDay, LocalDate lastDay, int numberOfPerson);
    List<Triple> getAllRoomInHotelForNumberOfGuests(Long hotelId, int numberOfPersons);
}
