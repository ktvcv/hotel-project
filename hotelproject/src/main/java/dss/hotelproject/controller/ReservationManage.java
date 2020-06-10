package dss.hotelproject.controller;

import dss.hotelproject.Enum.Services;
import dss.hotelproject.Exceptions.AccessDeniedException;
import dss.hotelproject.Exceptions.NoDataFoundException;
import dss.hotelproject.Exceptions.NoRoomForCheckInException;
import dss.hotelproject.Model.Guest;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.Service.CheckOutService;
import dss.hotelproject.Service.GuestService;
import dss.hotelproject.Service.ReservationManageService;
import dss.hotelproject.Service.ReservationService;
import dss.hotelproject.repos.GuestRepo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReservationManage {

    @Autowired
    ReservationService reservationService;
    @Autowired
    GuestService guestService;
    @Autowired
    ReservationManageService reservationManageService;
    @Autowired
    private CheckOutService checkOutService;

    @RequestMapping(value = "/addServiceToReservationAdmin",
            params = {"hotel_id", "reservationCode", "service", "number"},
            method = RequestMethod.POST)
    public JSONObject addServiceToReservation(@RequestParam("hotel_id") Long hotel_id,
                                              @RequestParam("reservationCode") String reservationCode,
                                              @RequestParam("DNI") String dni,
                                              @RequestParam("service") String service) throws AccessDeniedException, NoDataFoundException {
        Reservation reservation = reservationService.getReservationByCodeAndHotel(hotel_id,reservationCode);
        List<Services> servicesList = reservation.getServicesList();
        JSONObject jsonObject = new JSONObject();

        Guest guest = guestService.getGuestByDni(dni);
        if(guest==null)
        {
            jsonObject.put("Error", "No such guest");
            return jsonObject;
        }

        if(reservation.getGuest() == guest) {
            if (service.toLowerCase().equals(Services.Breakfast.name().toLowerCase()))
                servicesList.add(Services.Breakfast);
            else if (service.toLowerCase().equals(Services.Laundry.name().toLowerCase()))
                servicesList.add(Services.Laundry);
            else if (service.toLowerCase().equals(Services.Parking.name().toLowerCase()))
                servicesList.add(Services.Parking);
            else {
                jsonObject.put("Error", "No such reservation");
                return jsonObject;
            }
        }
        else {
            jsonObject.put("Error", "No such guest with this reservation");
            return jsonObject;
        }

        jsonObject.put("Guest dni", guest.getDNI());
        jsonObject.put("Service", Services.valueOf(service));
        jsonObject.put("Price", Services.valueOf(service).getServicePrice());
        jsonObject.put("Room", reservation.getRoom().getRoomNumber());
        return jsonObject;

    }
    @RequestMapping(value="/cancel",
            params = {"hotel_id", "reservation_code"},
            method= RequestMethod.POST)
    public String cancelReservation(@RequestParam("hotel_id") Long hotel_id,
                                    @RequestParam("reservation_code") String code)
    {
        double charge = reservationService.cancelReservation(hotel_id, code);
        if(charge == -1)
            return "No such reservation";

        return "Amount of charge "+ charge;

    }

    @RequestMapping(value="/getReservation",
            params = {"hotel_id", "reservation_code"},
            method= RequestMethod.GET)
    public Reservation getInformationOfReservation(@RequestParam("hotel_id") Long hotel_id,
                                                   @RequestParam("reservation_code") String code)
    {
        return reservationService.getReservationByCodeAndHotel(hotel_id,code);
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
    public  String checkOut(@RequestParam("hotel_id") Long hotel_id,
                            @RequestParam("reservation_code") String code)
    {
        Reservation reservation = reservationService.getReservationByCodeAndHotel(hotel_id,code);
        if(reservation == null)
            return "Reason: no such reservation ";
        double price = checkOutService.checkOut(hotel_id, reservation.getConfirmationCode());
        return "Additional price : " + price;
    }

    @RequestMapping(value="/checkIn",
            params = {"hotel_id", "room_id", "reservation_code"},
            method= RequestMethod.POST)
    public Reservation checkIn( @RequestParam("hotel_id") Long hotel_id,
                                @RequestParam("reservation_code") String code,
                                @RequestParam("room_id") String room_number) throws NoRoomForCheckInException {
        if(reservationService.checkIn(room_number, hotel_id, code))
            return reservationService.getReservationByCodeAndHotel(hotel_id, code);
        else return null;

    }

}
