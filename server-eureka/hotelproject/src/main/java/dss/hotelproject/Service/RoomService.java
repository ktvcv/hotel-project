package dss.hotelproject.Service;

import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Enum.RoomType;
import dss.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import dss.hotelproject.Interfaces.ISearchForFreeRooms;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.repos.RoomRepo;
import dss.hotelproject.util.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class RoomService implements ISearchForFreeRooms {

    @Autowired
    private RoomRepo roomRepo;

    /**
     * Method finds type of room, category of room
     * and number of free room in each type and category
     * @param hotelId : id of hotel in which searching room
     * @param firstDay : first day for searching
     * @param lastDay :
     * @param numberOfPersons: depends on which type of rooms will be found
     * @return : return list of 4 data types
     */
    @Override
    public List<Triple> findFreeTypeRoomsForPeriod(Long hotelId, LocalDate firstDay, LocalDate lastDay, int numberOfPersons, List<Reservation> reservations) {
        List<Triple> availableRoom = getAllRoomInHotelForNumberOfGuests(hotelId, numberOfPersons);

        if(reservations.isEmpty())
        {
            return availableRoom;
        }
        else{
            reservations.forEach(x -> availableRoom.forEach(
                    y -> {
                        if((y.getRoomType().equals(x.getRoomType()))
                                &&(y.getRoomTypeByMaxNumOfPerson().equals(x.getRoomTypeByMaxNumOfPerson())))
                            y.setNumber(y.getNumber() - 1);
                    }
            ));

            availableRoom.removeIf(x -> x.getNumber() <= 0);

        }
        return availableRoom;
    }

    @Override
    public List<Room> getFreeRoomsForCheckIn(Long hotelId, Reservation reservation) {
        List<Room> roomsInHotel = roomRepo.findAllByHotelId(hotelId);
        List<Room> freeRooms = new ArrayList<>();
        try{
            roomsInHotel.forEach(x -> {
                if ((x.getRoomStatus() == RoomStatus.FREE)
                        && (x.getType() == reservation.getRoomType())
                        && (x.getTypeOfPerson() == reservation.getRoomTypeByMaxNumOfPerson()))
                    freeRooms.add(x);
            });

            return freeRooms;
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        return Collections.emptyList();

    }

    @Override
    public List<Triple> getAllRoomInHotelForNumberOfGuests(Long hotelId, int numberOfPersons) {
        List<Room> rooms = roomRepo.findAllByHotelId(hotelId);

        System.out.println(rooms);
        List<Triple> roomsInHotel = new ArrayList<>();

        RoomType[] roomTypes = RoomType.values();
        RoomTypeByMaxNumOfPerson[] roomTypeOfPeople = RoomTypeByMaxNumOfPerson.values();

        for (RoomType rt: roomTypes)
            for(RoomTypeByMaxNumOfPerson rtP: roomTypeOfPeople)
                roomsInHotel.add(new Triple(rt,rtP,0,rt.getPriceByType()*rtP.getNumberOfPersonsByType()));

        roomsInHotel.forEach(x ->
                rooms.forEach(y ->
                        {
                            if((x.getRoomType().equals(y.getType()))
                                    &&(x.getRoomTypeByMaxNumOfPerson().equals(y.getTypeOfPerson()))
                                    &&(y.getTypeOfPerson().getNumberOfPersonsByType()>=numberOfPersons))
                                x.setNumber(x.getNumber()+1);
                        }
                ));

        roomsInHotel.removeIf(x -> x.getNumber()==0);
        return roomsInHotel;
    }

}
