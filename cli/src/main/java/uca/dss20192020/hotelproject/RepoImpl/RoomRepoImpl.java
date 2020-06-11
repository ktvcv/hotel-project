package uca.dss20192020.hotelproject.RepoImpl;


import uca.dss20192020.hotelproject.Interfaces.IRoomRepo;
import uca.dss20192020.hotelproject.Model.Hotel;
import uca.dss20192020.hotelproject.Model.Reservation;
import uca.dss20192020.hotelproject.Model.Room;

import java.util.List;
import java.util.Optional;

public class RoomRepoImpl implements IRoomRepo {

    HotelRepoImpl hotelRepo = new HotelRepoImpl();

    @Override
    public Room getRoomById(Long roomId, Long hotelId)
    {
        List<Room> rooms = getAllRooms(hotelId);

        Optional<Room> room = rooms
                .stream()
                .filter(x -> x.getId().equals(roomId))
                .findAny();
        return room.orElse(null);
    }

    @Override
    public void saveNewRoom(Long hotelId, Room room) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        List<Room> roomList = hotel.getRooms();
        roomList.add(room);

        hotel.setRooms(roomList);
        hotelRepo.saveHotel(hotel);
    }

    @Override
    public boolean removeRoom(Long hotelId, Long roomId) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        if(hotel != null){
            List<Room> rooms = hotel.getRooms();
            if(rooms.removeIf(x -> x.getId().equals(roomId)))
            {
                hotel.setRooms(rooms);
                hotelRepo.saveHotel(hotel);
                return true;
            }
            else
                return false;
        }
        else
            return false;

    }

    @Override
    public List<Room> getAllRooms(Long hotelId) {
        Hotel hotel = hotelRepo.getHotel(hotelId);
        return hotel.getRooms();
    }


}
