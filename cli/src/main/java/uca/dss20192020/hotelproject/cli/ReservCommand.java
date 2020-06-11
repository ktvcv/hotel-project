package uca.dss20192020.hotelproject.cli;

import uca.dss20192020.hotelproject.Enum.RoomType;
import uca.dss20192020.hotelproject.Enum.RoomTypeByMaxNumOfPerson;
import uca.dss20192020.hotelproject.Model.Guest;
import uca.dss20192020.hotelproject.Model.Reservation;
import uca.dss20192020.hotelproject.RepoImpl.GuestRepoImpl;
import uca.dss20192020.hotelproject.util.GeneratorHelper;

import java.nio.charset.MalformedInputException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservCommand implements Command {

    private final GuestRepoImpl guestRepo = new GuestRepoImpl();

    private final Long hotelId;
    private final LocalDate firstDay;
    private final LocalDate lastDay;
    private final RoomType roomType;
    private final RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson;

    public ReservCommand(Long hotelId, LocalDate firstDay, LocalDate lastDay, RoomType roomType, RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson) {
        this.hotelId = hotelId;
        this.firstDay = firstDay;
        this.lastDay = lastDay;
        this.roomType = roomType;
        this.roomTypeByMaxNumOfPerson = roomTypeByMaxNumOfPerson;
    }

    Scanner sc = new Scanner(System.in);

    @Override
    public void execute() throws MalformedInputException {
            System.out.println("Enter DNI:");
            String dni = sc.next();
            CreateGuestCommand createGuestCommand = new CreateGuestCommand(dni,hotelId);
            Guest guest= guestRepo.getGuestByDni(hotelId,dni);
            System.out.println(guest);
            if (!guestRepo.ifGuestExists(hotelId, dni))
                createGuestCommand.execute();

            Reservation.ReservationBuilder reservationBuilder = Reservation.ReservationBuilder
                    .aReservation()
                    .withDateFrom(firstDay)
                    .withDateTo(lastDay)
                    .withNumberOfPersons(roomTypeByMaxNumOfPerson.getNumberOfPersonsByType())
                    .withTotalCost(getTotalCost(roomType, firstDay, lastDay,roomTypeByMaxNumOfPerson))
                    .withRoomType(roomType)
                    .withRoomTypeOfPerson(roomTypeByMaxNumOfPerson)
                    .withGuest(guestRepo.getGuestByDni(hotelId,dni))
                    .withConfirmationCode(GeneratorHelper.generateReservationCode())
                    .withServicesList(new ArrayList<>());

            AddCardCommand addCardCommand = new AddCardCommand(reservationBuilder,hotelId);
            addCardCommand.execute();

    }

    private double getTotalCost(RoomType roomType, LocalDate firstDay, LocalDate lastDay, RoomTypeByMaxNumOfPerson roomTypeByMaxNumOfPerson) {
        return roomType.getPriceByType() * ChronoUnit.DAYS.between(firstDay,lastDay) * roomTypeByMaxNumOfPerson.getNumberOfPersonsByType();
    }
}
