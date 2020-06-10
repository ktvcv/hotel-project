package dss.hotelproject.cli.cli;


import dss.hotelproject.cli.Exceptions.NoDataFoundException;
import dss.hotelproject.cli.Model.Reservation;
import dss.hotelproject.cli.Service.ReservationService;
import dss.hotelproject.cli.Service.RoomService;
import dss.hotelproject.cli.repos.HotelRepo;
import dss.hotelproject.cli.util.Triple;
import org.springframework.stereotype.Component;

import java.nio.charset.MalformedInputException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component
public class FindCommand implements Command{

    private ReservationService reservationService;
    private RoomService roomService;

    private ReservCommand reservCommand;

    private ChoiceCommand choiceCommand;

    private HotelRepo hotelRepo;
    private final Scanner sc = new Scanner(System.in);

    @Override
    public void execute() throws MalformedInputException, NoDataFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        System.out.println("Enter first day  (format dd/MM/yyyy):");
        String firstDay = sc.next();
        System.out.println("Enter last day  (format dd/MM/yyyy):");
        String lastDay = sc.next();
        System.out.println("Enter last day  (format dd/MM/yyyy):");
        Long hotelId = sc.nextLong();

        LocalDate localDateFirstDay = LocalDate.parse(firstDay, formatter);
        LocalDate localDateLastDay = LocalDate.parse(lastDay, formatter);

        if(!checkDateInput(localDateFirstDay,localDateLastDay))
        {
            System.out.println("Dates with error");
            this.execute();
        }
        System.out.println("Enter number of persons:");
        int numberOfPer = sc.nextInt();
        List<Reservation> reservations = reservationService.getReservationsWithinDates(hotelId,localDateFirstDay,localDateLastDay);

        List<Triple> available = (roomService.findFreeTypeRoomsForPeriod
                (hotelId, localDateFirstDay, localDateLastDay, numberOfPer, reservations));

        for (int i = 0; i < available.size(); i++) {
            System.out.println("Index:" + i + ". " + available.get(i).toString() + "\n price: " + available.get(i).getPrice() + " euros" +
                    "\n number of free rooms: " + available.get(i).getNumber());
        }
        System.out.println("Select index of room");
        int room = sc.nextInt();
        reservCommand.setRoomType(available.get(room).getRoomType());
        reservCommand.setFirstDay(localDateFirstDay);
        reservCommand.setLastDay(localDateLastDay);
        reservCommand.setRoomTypeByMaxNumOfPerson(available.get(room).getRoomTypeByMaxNumOfPerson());
        reservCommand.setHotel(hotelRepo.findHotelById(hotelId));

        System.out.println("If you want to make reservation for your dates? y/exit");
        String choice = sc.next();
            if (choice.toLowerCase().equals("y")) {
                reservCommand.execute();
            }
            else if (choice.toLowerCase().equals("exit")) {
                choiceCommand.execute();
            }

    }

    private boolean checkDateInput(LocalDate firstDay, LocalDate lastDay)
    {
        return firstDay.isBefore(lastDay) && firstDay.isAfter(LocalDate.now());
    }
}
