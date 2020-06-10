package dss.hotelproject.controller;

import dss.hotelproject.Enum.RoomType;
import dss.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import dss.hotelproject.Exceptions.AccessDeniedException;
import dss.hotelproject.Model.CreditCard;
import dss.hotelproject.Model.Guest;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.Payment.CreditCardPayment;
import dss.hotelproject.Service.ReservationService;
import dss.hotelproject.repos.GuestRepo;
import dss.hotelproject.repos.HotelRepo;
import dss.hotelproject.repos.ReservationRepo;
import dss.hotelproject.util.GeneratorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@RestController
public class GuestController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepo reservationRepo;

    @Autowired
    private GuestRepo guestRepo;

    @Autowired
    private HotelRepo hotelRepo;

    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    @RequestMapping(value = "/reserveRoom",
            params = {"hotel_id", "dateFrom", "dateTo", "numberOfPerson", "roomType", "dni", "confirmationCode", "cardCode", "cardCVC", "cardMonth", "cardYear"},
            method = RequestMethod.POST)
    public Reservation saveReservation(@RequestParam("hotel_id") Long hotel_id,
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
        if(guestRepo.findGuestByDNI(guestDNI) == null) {
            return null;
        }
        String code = GeneratorHelper.generateReservationCode();
        Reservation reservation = Reservation.ReservationBuilder.aReservation()
                .withRoomType(RoomType.valueOf(roomType))
                .withRoomTypeByMaxNumOfPerson(RoomTypeByMaxNumOfPerson.valueOf(roomTypeByPrice))
                .withDateFrom(dateFrom)
                .withDateTo(dateTo)
                .withGuest(guestRepo.findGuestByDNI(guestDNI))
                .withNumberOfPersons(numberOfPerson)
                .withConfirmationCode(code)
                .withHotel(hotelRepo.findHotelById(hotel_id))
                .withPaymentMethod(new CreditCardPayment(new CreditCard(cardCode,cardCVC,cardMonth,cardYear)))
                .build();

        reservationRepo.save(reservation);
        logger.info("Your reservation code: " + reservation.getConfirmationCode());
        return reservation;
    }


    @RequestMapping(value = "/registerGuest",
            params = {"hotel_id", "dni", "name", "surname", "email", "phone"},
            method = RequestMethod.POST)
    public ResponseEntity<String> registerGuest(@RequestParam("hotel_id") Long hotel_id,
                                                @Pattern(message = "Data is not correct: ${validatedValue}",
                                                        regexp = "\\d{8}[A-HJ-NP-TV-Z]")
                                                @RequestParam("dni") String dni,
                                                @Valid @Pattern(message = "Data is not correct: ${validatedValue}",
                                                        regexp = "^[a-zA-Z]{3,15}$")
                                                @RequestParam("name") String name,
                                                @Valid @Pattern(message = "Data is not correct: ${validatedValue}",
                                                        regexp = "^[a-zA-Z]{3,15}$")
                                                @RequestParam("surname") String surname,
                                                @Valid @Pattern(message = "Data is not correct: ${validatedValue}",
                                                        regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")
                                                @RequestParam("email") String email,
                                                @Valid @Pattern(message = "Data is not correct: ${validatedValue}",
                                                        regexp = "^[0-9]{8,10}$")
                                                @RequestParam("phone") String phone)

    {
        Guest guest = Guest.GuestBuilder.aGuest()
                .withDNI(dni)
                .withMail(email)
                .withName(name)
                .withSurname(surname)
                .withMobile(phone)
                .build();
        if(guestRepo.findGuestByDNI(dni)!=null)
        {
            logger.warn("guest already created");
            return ResponseEntity.badRequest().body("Guest already created");
        }
        guestRepo.save(guest);
        logger.info("guest created");

        return new ResponseEntity<>(guest.toString(), HttpStatus.OK);
    }

    @RequestMapping(value="/cancel",
            params = {"hotel_id", "reservation_code"},
            method= RequestMethod.POST)
    public ResponseEntity<String> cancelReservation(@RequestParam("hotel_id") Long hotel_id,
                                                    @RequestParam("reservation_code") String code)
    {
        double charge = reservationService.cancelReservation(hotel_id, code);
        if(charge == -1)
            return ResponseEntity.badRequest().body("No such reservation");

        return new ResponseEntity<>("Amount of charge "+ charge, HttpStatus.OK);

    }

    @RequestMapping(value="/getReservation",
            params = {"hotel_id", "reservation_code"},
            method= RequestMethod.GET)
    public ResponseEntity<Reservation> getInformationOfReservation(@RequestParam("hotel_id") Long hotel_id,
                                                                   @RequestParam("reservation_code") String code)
    {
        Reservation reservation = reservationRepo.findReservationByConfirmationCode(code);
        if (reservation == null)
            return ResponseEntity.badRequest().body(reservation);
        else return new ResponseEntity<>(reservation, HttpStatus.NOT_FOUND);
    }

}
