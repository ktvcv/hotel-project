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


    @RequestMapping(value="/freeRoomNow",
            params = {"hotel_id", "reservation_code"},
            method= RequestMethod.POST)
    public void selectRoomForCheckIn(@RequestParam("hotel_id") Long hotel_id,
                                     @RequestParam("reservation_code") String code,
                                     @RequestParam("room_id") String room_number)
    {
        reservationService.checkIn(room_number, hotel_id, code);
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
