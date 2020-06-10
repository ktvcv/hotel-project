package dss.hotelproject.cli;

import dss.hotelproject.Enum.RoomType;
import dss.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import dss.hotelproject.Exceptions.NoDataFoundException;
import dss.hotelproject.Model.Hotel;
import dss.hotelproject.Model.Reservation;
import dss.hotelproject.repos.GuestRepo;
import dss.hotelproject.repos.HotelRepo;
import dss.hotelproject.util.GeneratorHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.MalformedInputException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

@Component
public class ReservCommand implements Command {
    @Autowired
    private GuestRepo guestRepo;

    @Autowired
    private CreateGuestCommand createGuestCommand ;

    @Autowired
    HotelRepo hotelRepo;
    @Autowired
    private AddCardCommand addCardCommand;

    private LocalDate firstDay;
    private LocalDate lastDay;
    private RoomType roomType;
    private RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson;
    private Hotel hotel;

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setFirstDay(LocalDate firstDay) {
        this.firstDay = firstDay;
    }

    public void setLastDay(LocalDate lastDay) {
        this.lastDay = lastDay;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public void setRoomTypeByMaxNumOfPerson(RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson) {
        this.roomTypeByMaxNumOfPerson = roomTypeByMaxNumOfPerson;
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public void execute() throws MalformedInputException, NoDataFoundException {
            System.out.println("Enter DNI:");
            String dni = sc.next();

            if (guestRepo.findGuestByDNI(dni) != null)
                createGuestCommand.execute();

            Reservation.ReservationBuilder reservationBuilder = Reservation.ReservationBuilder
                    .aReservation()
                    .withDateFrom(firstDay)
                    .withDateTo(lastDay)
                    .withNumberOfPersons(roomTypeByMaxNumOfPerson.getNumberOfPersonsByType())
                    .withTotalCost(getTotalCost(roomType, firstDay, lastDay,roomTypeByMaxNumOfPerson))
                    .withRoomType(roomType)
                    .withHotel(hotel)
                    .withRoomTypeByMaxNumOfPerson(roomTypeByMaxNumOfPerson)
                    .withGuest(guestRepo.findGuestByDNI(dni))
                    .withConfirmationCode(GeneratorHelper.generateReservationCode())
                    .withServicesList(new ArrayList<>());

            addCardCommand.setReservationBuilder(reservationBuilder);
            addCardCommand.execute();

    }

    private double getTotalCost(RoomType roomType, LocalDate firstDay, LocalDate lastDay, RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson) {
        return roomType.getPriceByType() * ChronoUnit.DAYS.between(firstDay,lastDay) * roomTypeByMaxNumOfPerson.getNumberOfPersonsByType();
    }
}
