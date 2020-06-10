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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
public class Application {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private CheckOutService checkOutService;

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private GuestRepo guestRepo;

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
    public List<Triple> getAvailableRooms(@RequestParam("hotel_id") Long hotel_id,
                                            @RequestParam("dateFrom") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
                                            @RequestParam("dateTo") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo,
                                            @RequestParam("numberOfPerson") @Max(3) int numberOfPerson) throws NoDataFoundException {
        if(dateTo.isBefore(dateFrom) || dateFrom.isBefore(LocalDate.now())) {
            throw new NoDataFoundException("Incorrect dated");
        }
            List<Reservation> reservations = reservationService.getReservationsWithinDates(hotel_id,dateFrom, dateTo);
        return roomService.findFreeTypeRoomsForPeriod
                (hotel_id, dateFrom, dateTo, numberOfPerson, reservations);

    }
    @RequestMapping(value = "/index",
            method = RequestMethod.GET)
    public String index()
    {
        return "Welcome!";
    }

    @Autowired
    RoomRepo roomRepo;

    @RequestMapping(value = "/allReservations",
            params = {"hotel_id"},
            method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getAllReservation(@RequestParam("hotel_id") Long hotel_id)
    {
        List<Reservation> list = reservationRepo.findAllByHotelId(hotel_id);
        if(list == null)
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
        else  return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/allRooms",
                    params = {"hotel_id"},
                    method = RequestMethod.GET)
    public ResponseEntity<List<Room>> getAllRooms(@RequestParam("hotel_id") Long hotel_id)
    {
        List<Room> list = roomRepo.findAllByHotelId(hotel_id);
        if(list == null)
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NOT_FOUND);
         else  return new ResponseEntity<>(list, HttpStatus.OK);

    }

    @RequestMapping(value = "/allGuests",
            method = RequestMethod.GET)
    public ResponseEntity<List<Guest>> getAllGuest()
    {
        List<Guest> list = (List<Guest>) guestRepo.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value="/freeRoomNow",
            params = {"hotel_id", "reservation_code"},
            method= RequestMethod.POST)
    public void selectRoomForCheckIn(@RequestParam("hotel_id") Long hotel_id,
                                     @RequestParam("reservation_code") String code,
                                     @RequestParam("room_id") String room_number)
    {
        reservationService.checkIn(room_number, hotel_id, code);
    }


    @RequestMapping(value="/checkOut", params = {"hotel_id", "reservation_code"}, method= RequestMethod.POST)
    public  ResponseEntity<String> checkOut(@RequestParam("hotel_id") Long hotel_id,
                         @RequestParam("reservation_id") Long id)
    {
        Reservation reservation = reservationRepo.findReservationById(id);
        if(reservation == null)
            return new ResponseEntity<>("Reason: no such reservation ",HttpStatus.NOT_FOUND);
        double price = checkOutService.checkOut(hotel_id, reservation.getConfirmationCode());
        return new ResponseEntity<>("Additional price : " + price, HttpStatus.OK);
    }

    @RequestMapping(value="/roomsForCheckIn",
            params = {"hotel_id", "reservation_code"},
            method= RequestMethod.GET)
    public ResponseEntity<List<Room>> findRoomForCheckIn(@RequestParam("hotel_id") Long hotel_id,
                                         @RequestParam("reservation_code") String code)
    {
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(code);
        List<Room> rooms = roomService.getFreeRoomsForCheckIn(hotel_id,reservation);
        if(rooms == null)
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(rooms, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/checkIn",
            params = {"hotel_id", "room_id", "reservation_code"},
            method= RequestMethod.POST)
    public ResponseEntity<Reservation> checkIn( @RequestParam("hotel_id") Long hotel_id,
                           @RequestParam("reservation_code") String code,
                           @RequestParam("room_id") String room_number) throws NoRoomForCheckInException {
        if(reservationService.checkIn(room_number, hotel_id, code))
            return new ResponseEntity<>(reservationRepo.findReservationByConfirmationCode(code), HttpStatus.OK);
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reservationRepo.findReservationByConfirmationCode(code));

    }
    @RequestMapping(value = "/addServiceToReservationAdmin",
            params = {"hotel_id", "reservationCode", "service", "number"},
            method = RequestMethod.POST)
    public Response addServiceToReservation(@RequestParam("hotel_id") Long hotel_id,
                                                            @RequestParam("reservationCode") String reservationCode,
                                                            @RequestParam("DNI") String dni,
                                                            @RequestParam("service") String service) throws AccessDeniedException, NoDataFoundException {
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(reservationCode);
        List<Services> servicesList = reservation.getServicesList();
        Guest guest = guestRepo.findGuestByDNI(dni);
        if(guest==null)
        {
            Response.ResponseBuilder rb = Response.status(Response.Status.BAD_REQUEST);
            return rb.header("Status", "No such guest")
                    .build();
        }

        if(reservation.getGuest() == guest) {
            if (service.toLowerCase().equals(Services.Breakfast.name().toLowerCase()))
                servicesList.add(Services.Breakfast);
            else if (service.toLowerCase().equals(Services.Laundry.name().toLowerCase()))
                servicesList.add(Services.Laundry);
            else if (service.toLowerCase().equals(Services.Parking.name().toLowerCase()))
                servicesList.add(Services.Parking);
            else {
                Response.ResponseBuilder rb = Response.status(Response.Status.BAD_REQUEST);
                return rb.header("Status", "No such reservation")
                        .build();
            }
        }
        else {
            Response.ResponseBuilder rb = Response.status(Response.Status.BAD_REQUEST);
            return rb.header("Reason", "This guest does not possess this reservation")
                    .build();
        }
        reservation.setServicesList(servicesList);
        reservationRepo.save(reservation);
        Response.ResponseBuilder rbSuccess = Response.status(Response.Status.OK);
        return rbSuccess.header("Guest dni", guest.getDNI())
                 .header("Service", Services.valueOf(service))
                 .header("Price", Services.valueOf(service).getServicePrice())
                 .header("Room", reservation.getRoom().getRoomNumber())
                .build();
    }

    @RequestMapping(value = "/makeRoomUnavailable",
            params = {"hotel_id", "room_number"},
            method = RequestMethod.POST)
    public ResponseEntity<String> makeRoomUnavailable(@RequestParam("hotel_id") Long hotel_id,
                                      @RequestParam("room_number") String room_number)
    {
        Room room = roomRepo.findRoomByRoomNumberAndHotelId(room_number, hotel_id);
        if(room.getRoomStatus() == RoomStatus.FREE) {
            room.setRoomStatus(RoomStatus.RESERVED);
            roomRepo.save(room);
            return ResponseEntity.status(HttpStatus.OK).body("Room is reserved");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room is already reserved or occupied");
    }

    @RequestMapping(value = "/makeRoomAvailable",
            params = {"hotel_id", "room_number"},
            method = RequestMethod.POST)
    public ResponseEntity<String> makeRoomAvailable(@RequestParam("hotel_id") Long hotel_id,
                                                    @RequestParam("room_number") String room_number)
    {
        Room room = roomRepo.findRoomByRoomNumberAndHotelId(room_number, hotel_id);
        if(room.getRoomStatus() == RoomStatus.RESERVED) {
            room.setRoomStatus(RoomStatus.FREE);
            roomRepo.save(room);
            return ResponseEntity.status(HttpStatus.OK).body("Room is reserved");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Room is already reserved or occupied");
    }
}
