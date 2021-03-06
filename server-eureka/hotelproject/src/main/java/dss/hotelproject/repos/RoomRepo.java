package dss.hotelproject.repos;


import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoomRepo extends CrudRepository<Room, Long> {
    List<Room> findAllByHotelId(Long id);
    Room findRoomById(Long id);
    List<Room> findAllByRoomStatus(RoomStatus roomstatus);
    Room findRoomByRoomNumberAndHotelId(String number, Long hotelId);
}
