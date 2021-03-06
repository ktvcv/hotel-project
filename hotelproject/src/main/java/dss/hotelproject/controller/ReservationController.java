package dss.hotelproject.controller;

import dss.hotelproject.Enum.RoomType;
import dss.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import dss.hotelproject.Exceptions.AccessDeniedException;
import dss.hotelproject.Model.CreditCard;
import dss.hotelproject.Model.Guest;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Model.Room;
import dss.hotelproject.Payment.CreditCardPayment;
import dss.hotelproject.Service.GuestService;
import dss.hotelproject.Service.HotelService;
import dss.hotelproject.Service.ReservationService;
import dss.hotelproject.Service.RoomService;
import dss.hotelproject.util.GeneratorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    ReservationService reservationService;
    @Autowired
    GuestService guestService;
    @Autowired
    RoomService roomService;
    @Autowired
    HotelService hotelService;

    @GetMapping(value = "/allReservations/{hotel_id}")
    public Reservation[] getAllReservation(@PathVariable Long hotel_id)
    {
        return reservationService.getAllByHotelId(hotel_id).toArray(new Reservation[0]);
    }


    @RequestMapping(value = "/reserveRoom",
            params = {"hotel_id", "dateFrom", "dateTo", "numberOfPerson", "roomType", "dni", "cardCode", "cardCVC", "cardMonth", "cardYear"},
            method = RequestMethod.POST)
    public String saveReservation(@RequestParam("hotel_id") Long hotel_id,
                                       @RequestParam("dateFrom") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateFrom,
                                       @RequestParam("dateTo") @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate dateTo,
                                       @RequestParam("numberOfPerson") int numberOfPerson,
                                       @RequestParam("roomType") String roomType,
                                       @RequestParam("roomTypeByPerson") String roomTypeByPrice,
                                       @RequestParam("dni") String guestDNI,
                                       @Valid @Pattern(message = "Data is not correct: ${validatedValue}", regexp = "^[0-9]{16}$")
                                       @RequestParam("cardCode") String cardCode,
                                       @Valid @Pattern(message = "Data is not correct: ${validatedValue}", regexp = "^[0-9]{3}$")
                                       @RequestParam("cardCVC") String cardCVC,
                                       @Pattern(message = "Data is not correct: ${validatedValue}", regexp = "^(1[0-2]|[1-9])$")
                                       @RequestParam("cardMonth") String cardMonth,
                                       @Pattern(message = "Data is not correct: ${validatedValue}", regexp = "^[2][0-9]$")
                                       @RequestParam("cardYear") String cardYear) throws AccessDeniedException
    {
        Guest guest = guestService.getGuestByDni(guestDNI);
        if(guest == null) {
            return null;
        }
        String code = GeneratorHelper.generateReservationCode();
        Reservation reservation = Reservation.ReservationBuilder.aReservation()
                .withRoomType(RoomType.valueOf(roomType))
                .withRoomTypeByMaxNumOfPerson(RoomTypeByMaxNumOfPerson.valueOf(roomTypeByPrice))
                .withDateFrom(dateFrom)
                .withDateTo(dateTo)
                .withGuest(guest)
                .withServicesList(new ArrayList<>())
                .withNumberOfPersons(numberOfPerson)
                .withConfirmationCode(code)
                .withHotel(hotelService.getHotelById(hotel_id))
                .withPaymentMethod(new CreditCardPayment(new CreditCard(cardCode,cardCVC,cardMonth,cardYear)))
                .build();

        reservation.setTotalCost(getTotalCost(reservation.getRoomType(),reservation.getDateFrom(), reservation.getDateTo(),reservation.getRoomTypeByMaxNumOfPerson()));
        reservationService.saveReservation(reservation);
        return reservation.toString();
    }
    private double getTotalCost(RoomType roomType, LocalDate firstDay, LocalDate lastDay, RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson) {
        return roomType.getPriceByType() * ChronoUnit.DAYS.between(firstDay,lastDay) * roomTypeByMaxNumOfPerson.getNumberOfPersonsByType();
    }


    @RequestMapping(value="/getReservation",
            params = {"hotel_id", "reservation_code"},
            method= RequestMethod.GET)
    public Reservation getInformationOfReservation(@RequestParam("hotel_id") Long hotel_id,
                                                   @RequestParam("reservation_code") String code)
    {
        return reservationService.getReservationByCodeAndHotel(hotel_id,code);
    }

}

