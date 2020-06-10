package dss.hotelproject.controller;

import dss.hotelproject.Model.Guest;
import dss.hotelproject.Service.GuestService;
import dss.hotelproject.Service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
public class GuestController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private GuestService guestService;


    private static final Logger logger = LoggerFactory.getLogger(RoomManageController.class);

    @RequestMapping(value = "/registerGuest",
            params = {"hotel_id", "dni", "name", "surname", "email", "phone"},
            method = RequestMethod.POST)
    public Guest registerGuest(@RequestParam("hotel_id") Long hotel_id,
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
        if(guestService.getGuestByDni(dni)!=null)
        {
            logger.warn("guest already created");
            return null;
        }
        guestService.saveGuest(guest);
        logger.info("guest created");

        return guest;
    }


}
