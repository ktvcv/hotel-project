package dss.hotelproject.Interfaces;

import dss.hotelproject.Model.Room;

public interface IRoomService {
    void saveRoom(Room room);
    Room getRoomByHotelAndNumber(Long id, String number);
}
