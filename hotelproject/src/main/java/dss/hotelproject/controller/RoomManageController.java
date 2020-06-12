package dss.hotelproject.controller;

import dss.hotelproject.Enum.RoomStatus;
import dss.hotelproject.Enum.Services;
import dss.hotelproject.Exceptions.AccessDeniedException;
import dss.hotelproject.Exceptions.NoDataFoundException;
import dss.hotelproject.Exceptions.NoRoomForCheckInException;
import dss.hotelproject.Model.Guest;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.Service.CheckOutService;
import dss.hotelproject.Service.ReservationManageService;
import dss.hotelproject.Service.ReservationService;
import dss.hotelproject.Service.RoomService;
import dss.hotelproject.repos.GuestRepo;
import dss.hotelproject.repos.ReservationRepo;
import dss.hotelproject.repos.RoomRepo;
import dss.hotelproject.util.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
public class RoomManageController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;


    /**
     *
     * @param hotel_id - unique id of hotel
     * @param dateFrom: first day
     * @param dateTo: last day of reservation
     * @param numberOfPerson - maximum number for living in room
     * @return - returning all available rooms, it's type, price and number
     */
    @RequestMapping(value = "/availableRooms",
            params = {"hotel_id", "dateFrom", "dateTo", "numberOfPerson"},
            method = RequestMethod.GET)
    public Triple[] getAvailableRooms(@RequestParam("hotel_id") Long hotel_id,
                                    @RequestParam("dateFrom") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
                                    @RequestParam("dateTo") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo,
                                    @RequestParam("numberOfPerson") @Max(3) int numberOfPerson) throws NoDataFoundException {
        if(dateTo.isBefore(dateFrom) || dateFrom.isBefore(LocalDate.now())) {
            throw new NoDataFoundException("Incorrect dated");
        }
            List<Reservation> reservations = reservationService.getReservationsWithinDates(hotel_id,dateFrom, dateTo);
        return roomService.findFreeTypeRoomsForPeriod
                (hotel_id, dateFrom, dateTo, numberOfPerson, reservations).toArray(new Triple[0]);

    }
    @GetMapping(value = "/index/{name}")
    public String index(@PathVariable String name)
    {
        return "Welcome! + " + name;
    }

    @RequestMapping(value = "/makeRoomUnavailable",
            params = {"hotel_id", "room_number"},
            method = RequestMethod.POST)
    public String makeRoomUnavailable(@RequestParam("hotel_id") Long hotel_id,
                                      @RequestParam("room_number") String room_number)
    {
        Room room = roomService.getRoomByHotelAndNumber(hotel_id,room_number);        if(room.getRoomStatus() == RoomStatus.FREE) {
            room.setRoomStatus(RoomStatus.RESERVED);
            roomService.saveRoom(room);
            return "Room is reserved";
        }
        return "Room is already reserved or occupied";
    }

    @RequestMapping(value = "/makeRoomAvailable",
            params = {"hotel_id", "room_number"},
            method = RequestMethod.POST)
    public String makeRoomAvailable(@RequestParam("hotel_id") Long hotel_id,
                                                    @RequestParam("room_number") String room_number)
    {
        Room room = roomService.getRoomByHotelAndNumber(hotel_id,room_number);
        if(room.getRoomStatus() == RoomStatus.RESERVED) {
            room.setRoomStatus(RoomStatus.FREE);
            roomService.saveRoom(room);
            return "Room is reserved";
        }
        return "Room is already reserved or occupied";
    }

    @RequestMapping(value="/roomsForCheckIn",
            params = {"hotel_id", "reservation_code"},
            method= RequestMethod.GET)
    public List<Room> findRoomForCheckIn(@RequestParam("hotel_id") Long hotel_id,
                                         @RequestParam("reservation_code") String code) {
        Reservation reservation = reservationService.getReservationByCodeAndHotel(hotel_id, code);
        return roomService.getFreeRoomsForCheckIn(hotel_id, reservation);
    }
}
