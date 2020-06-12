package dss.hotelproject.controller;

import dss.hotelproject.Enum.Services;
import dss.hotelproject.Exceptions.AccessDeniedException;
import dss.hotelproject.Exceptions.NoDataFoundException;
import dss.hotelproject.Model.Guest;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Service.ExtrasService;
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
    ExtrasService extrasService;

    @RequestMapping(value = "/addServiceToReservationAdmin",
            params = {"hotel_id", "reservationCode", "service", "number"},
            method = RequestMethod.POST)
    public String addServiceToReservation(@RequestParam("hotel_id") Long hotel_id,
                                              @RequestParam("reservationCode") String reservationCode,
                                              @RequestParam("DNI") String dni,
                                              @RequestParam("service") String service) throws AccessDeniedException, NoDataFoundException {
        Reservation reservation = extrasService.getReservationByCodeAndHotel(hotel_id,reservationCode);
        List<Services> servicesList = reservation.getServicesList();
        JSONObject jsonObject = new JSONObject();

        Guest guest = extrasService.getGuestByDni(dni);
        if(guest==null)
        {
            jsonObject.put("Error", "No such guest");
            return jsonObject.toString();
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
                return jsonObject.toString();
            }
        }
        else {
            jsonObject.put("Error", "No such guest with this reservation");
            return jsonObject.toString();
        }

        jsonObject.put("Guest dni", guest.getDNI());
        jsonObject.put("Service", Services.valueOf(service));
        jsonObject.put("Price", Services.valueOf(service).getServicePrice());
        jsonObject.put("Room", reservation.getRoom().getRoomNumber());
        reservation.setServicesList(servicesList);
        extrasService.saveReservation(reservation);
        return jsonObject.toString();

    }
    @RequestMapping(value="/checkOut", params = {"hotel_id", "reservation_code"}, method= RequestMethod.POST)
    public  String checkOut(@RequestParam("hotel_id") Long hotel_id,
                            @RequestParam("reservation_code") String code)
    {
        Reservation reservation = extrasService.getReservationByCodeAndHotel(hotel_id,code);
        if(reservation == null)
            return "Reason: no such reservation ";
        double price = extrasService.checkOut(hotel_id, reservation.getConfirmationCode());
        return "Additional price : " + price;
    }



}
