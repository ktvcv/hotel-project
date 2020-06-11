package uca.dss20192020.hotelproject.Interfaces;

import uca.dss20192020.hotelproject.Model.Room;

import java.util.List;


public interface IRoomRepo {
    Room getRoomById(Long roomId,Long hotelId);
    void saveNewRoom(Long hotelId, Room  room);
    boolean removeRoom(Long hotelId, Long roomId);
    List<Room> getAllRooms(Long hotelId);

}
