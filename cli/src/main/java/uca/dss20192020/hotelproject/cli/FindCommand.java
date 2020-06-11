package uca.dss20192020.hotelproject.cli;

import uca.dss20192020.hotelproject.Service.ReservationService;
import uca.dss20192020.hotelproject.util.Triple;

import java.nio.charset.MalformedInputException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class FindCommand implements Command{

    private final Long hotelId;
    private final ReservationService reservationService = new ReservationService();
    Scanner sc = new Scanner(System.in);

    public FindCommand( Long hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public void execute() throws  MalformedInputException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

        System.out.println("Enter first day  (format dd/MM/yyyy):");
        String firstDay = sc.next();
        System.out.println("Enter last day  (format dd/MM/yyyy):");
        String lastDay = sc.next();

        LocalDate localDateFirstDay = LocalDate.parse(firstDay, formatter);
        LocalDate localDateLastDay = LocalDate.parse(lastDay, formatter);

        if(!checkDateInput(localDateFirstDay,localDateLastDay))
        {
            System.out.println("Dates with error");
            this.execute();
        }
        System.out.println("Enter number of persons:");
        int numberOfPer = sc.nextInt();

        List<Triple> available = (reservationService.findFreeTypeRoomsForPeriod
                (hotelId, localDateFirstDay, localDateLastDay, numberOfPer));

        for (int i = 0; i < available.size(); i++) {
            System.out.println("Index:" + i + ". " + available.get(i).toString() + "\n price: " + available.get(i).getPrice() + " euros" +
                    "\n number of free rooms: " + available.get(i).getNumber());
        }
        System.out.println("If you want to make reservation for your dates? y/exit");
        String choice = sc.next();
        ReservCommand reservCommand;
        ChoiceCommand choiceCommand = new ChoiceCommand();
        int room;
        if (choice.toLowerCase().equals("y")) {
            System.out.println("Select index of room");
            room = sc.nextInt();
            reservCommand = new ReservCommand(hotelId,localDateFirstDay, localDateLastDay, available.get(room).getRoomType(),available.get(room).getRoomTypeByMaxNumOfPerson());

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
