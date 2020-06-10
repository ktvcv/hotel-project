package dss.hotelproject.cli.Interfaces;


import dss.hotelproject.cli.Model.Room;

public interface IRoomService {
    void saveRoom(Room room);
    Room getRoomByHotelAndNumber(Long id, String number);
}
